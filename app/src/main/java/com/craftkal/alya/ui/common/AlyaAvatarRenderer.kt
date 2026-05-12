package com.craftkal.alya.ui.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.craftkal.alya.core.personality.DayMood

class AlyaAvatarRenderer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var currentMood: DayMood = DayMood.MOOD_BIASA

    private val facePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#FFE0BD") // Skin tone
        style = Paint.Style.FILL
    }

    private val eyePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    private val mouthPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    fun setMood(mood: DayMood) {
        currentMood = mood
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = width.coerceAtMost(height) / 2.5f

        // Draw Face
        canvas.drawCircle(centerX, centerY, radius, facePaint)

        // Draw Eyes based on mood
        when (currentMood) {
            DayMood.MOOD_CERIA -> {
                canvas.drawCircle(centerX - radius/3, centerY - radius/4, 15f, eyePaint)
                canvas.drawCircle(centerX + radius/3, centerY - radius/4, 15f, eyePaint)
                // Pipi merah
                val blushPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(80, 255, 100, 100) }
                canvas.drawCircle(centerX - radius/2, centerY, 20f, blushPaint)
                canvas.drawCircle(centerX + radius/2, centerY, 20f, blushPaint)
            }
            DayMood.MOOD_MAGER -> {
                canvas.drawLine(centerX - radius/2, centerY - radius/4, centerX - radius/6, centerY - radius/4, eyePaint)
                canvas.drawLine(centerX + radius/6, centerY - radius/4, centerX + radius/2, centerY - radius/4, eyePaint)
            }
            DayMood.MOOD_MELLOW -> {
                canvas.drawCircle(centerX - radius/3, centerY - radius/4, 10f, eyePaint)
                canvas.drawCircle(centerX + radius/3, centerY - radius/4, 10f, eyePaint)
            }
            else -> {
                canvas.drawCircle(centerX - radius/3, centerY - radius/4, 12f, eyePaint)
                canvas.drawCircle(centerX + radius/3, centerY - radius/4, 12f, eyePaint)
            }
        }

        // Draw Mouth
        when (currentMood) {
            DayMood.MOOD_CERIA -> canvas.drawArc(centerX - radius/3, centerY, centerX + radius/3, centerY + radius/2, 0f, 180f, false, mouthPaint)
            DayMood.MOOD_MAGER -> canvas.drawLine(centerX - radius/4, centerY + radius/3, centerX + radius/4, centerY + radius/3, mouthPaint)
            else -> canvas.drawArc(centerX - radius/3, centerY + radius/4, centerX + radius/3, centerY + radius/2, 0f, 180f, false, mouthPaint)
        }
    }
}
