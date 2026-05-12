package com.craftkal.alya.ui.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.sin

class ThinkingVisualizerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#6200EE")
        style = Paint.Style.FILL
    }

    private var startTime = System.currentTimeMillis()
    private val dotCount = 5
    private val dotRadius = 8f
    private val spacing = 30f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val time = (System.currentTimeMillis() - startTime) / 1000f
        
        val centerX = width / 2f
        val centerY = height / 2f
        val startX = centerX - (dotCount - 1) * spacing / 2f

        for (i in 0 until dotCount) {
            val alpha = ((sin(time * 5 + i * 0.8) + 1) / 2 * 255).toInt()
            dotPaint.alpha = alpha
            canvas.drawCircle(startX + i * spacing, centerY, dotRadius, dotPaint)
        }
        
        invalidate()
    }
}
