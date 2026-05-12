package com.craftkal.alya.core.engine

import com.craftkal.alya.core.personality.DayMood
import kotlin.random.Random

data class TypingRhythm(
    val baseDelayMs: Long,
    val charsPerSecond: Float,
    val thinkingDuration: Long
)

class TypingRhythmEngine {
    fun calculate(mood: DayMood): TypingRhythm {
        val baseDelay = when(mood) {
            DayMood.MOOD_MAGER -> Random.nextLong(1200, 2500)
            DayMood.MOOD_CERIA -> Random.nextLong(300, 600)
            DayMood.MOOD_MELLOW -> Random.nextLong(800, 1500)
            else -> Random.nextLong(500, 1000)
        }
        
        return TypingRhythm(
            baseDelayMs = baseDelay,
            charsPerSecond = 15f - (mood.ordinal * 2f),
            thinkingDuration = baseDelay / 2
        )
    }
}
