package com.example.snake

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.view.MotionEvent
import android.view.View

class SnakeBody{
    private var snakeTailX = mutableListOf<Int>()
    private var snakeTailY = mutableListOf<Int>()

    private val up = 1
    private val right = 2
    private val down = 3
    private val left = 4

    private val dim = 20

    private var heading = right

    fun initialize()
    {
        snakeTailX.add(0, 200)
        snakeTailY.add(0, 200)

        addTail()
        addTail()
        addTail()
        addTail()
        addTail()
        addTail()

        when((1..4).random())
        {
            1 -> heading = left
            2 -> heading = right
            3 -> heading = up
            4 -> heading = down
        }
    }

    fun addTail()
    {
        snakeTailX.add(snakeTailX.count(), snakeTailX[snakeTailX.count() - 1])
        snakeTailY.add(snakeTailY.count(), snakeTailY[snakeTailY.count() - 1])
    }

    fun moveTails()
    {
        //val dim = resources.getInteger(R.integer.dim);

        if(snakeTailX.count() > 1)
        {
            var i = snakeTailX.count() - 1

            while(i > 0) {
                snakeTailX[i] = snakeTailX[i-1]
                snakeTailY[i] = snakeTailY[i-1]
                i--
            }

            when(heading)
            {
                right -> snakeTailX[0] += dim
                left -> snakeTailX[0] -= dim
                up -> snakeTailY[0] -= dim
                down -> snakeTailY[0] += dim
            }
        }
    }

    fun moveSnake(motionEvent: MotionEvent, view: View)
    {
        when (motionEvent.action and MotionEvent.ACTION_MASK) {

            MotionEvent.ACTION_UP -> heading  =

                if (motionEvent.x >= view.width / 2) {
                    when (heading) {
                        up -> right
                        right -> down
                        down -> left
                        left -> up
                        else -> left
                    }
                } else {
                    when (heading) {
                        up -> left
                        left -> down
                        down -> right
                        right -> up
                        else -> left
                    }
                }
        }
    }

    fun drawTails(canvas: Canvas)
    {
        var i = 0

        while(i < snakeTailX.count()) {
            drawTail(canvas, snakeTailX[i], snakeTailY[i])
            i++
        }
    }

    private fun drawTail(canvas: Canvas, posX: Int, posY: Int)
    {
        //val dim = resources.getInteger(R.integer.dim);

        val shapeDrawable = ShapeDrawable(RectShape())

        shapeDrawable.setBounds( posX, posY, posX + dim, posY + dim)
        shapeDrawable.paint.color = Color.MAGENTA
        shapeDrawable.paint.style = Paint.Style.STROKE
        shapeDrawable.paint.strokeWidth = 2f
        shapeDrawable.draw(canvas)
    }
}