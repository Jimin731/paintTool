package com.example.tiltsensor2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    companion object{
        const val LINE =1
        const val CIRCLE =2
        const val RECTANGLE =3
        var curShape = LINE
        var curColor = Color.BLACK
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyGraphicView(this))


        title = "간단 그림판(개선)"
    }

    private class MyGraphicView(context: Context):View(context){

        var startX = 0f
        var startY = 0f
        var stopX = 100f
        var stopY = 100f
        /*--
        override fun onTouchEvent(event: MotionEvent?): Boolean {
            when (event!!.action){
                MotionEvent.ACTION_DOWN->{
                    startX = event.x.toInt().toFloat()
                    startY = event.y.toInt().toFloat()
                }
                MotionEvent.ACTION_MOVE,MotionEvent.ACTION_UP->{
                    stopX=event.x.toInt().toFloat()
                    stopY=event.y.toInt().toFloat()
                    this.invalidate()
                }
            }
            return true
        }
         */

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val paint = Paint()
            paint.isAntiAlias=true
            paint.strokeWidth = 5f
            paint.style=Paint.Style.STROKE
            paint.color= curColor

            when(curShape){
                LINE -> canvas.drawLine(startX, startY,
                    stopX, stopY,paint)
                CIRCLE->{
                    var radius = sqrt((stopX - startX).toDouble().pow(2.0) + (stopY - startY).toDouble()
                        .pow(2.0)
                    )
                    canvas.drawCircle(220f, 220f,radius.toFloat(),paint)
                }
                RECTANGLE-> canvas.drawRect(startX,
                    startY, startX+(stopX-startX), startY+(stopY-startY),paint)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.draw_line->{
                curShape = LINE //선
                setContentView(MyGraphicView(this))
                return true
            }
            R.id.draw_circle->{
                curShape = CIRCLE //원
                setContentView(MyGraphicView(this))
                return true
            }
            R.id.draw_rect->{
                curShape = RECTANGLE // 사각형
                setContentView(MyGraphicView(this))
                return true
            }
            R.id.red->{
                curColor = Color.RED
                setContentView(MyGraphicView(this))
                return true
            }
            R.id.green->{
                curColor = Color.GREEN
                setContentView(MyGraphicView(this))
                return true
            }
            R.id.blue->{
                curColor=Color.BLUE
                setContentView(MyGraphicView(this))
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}