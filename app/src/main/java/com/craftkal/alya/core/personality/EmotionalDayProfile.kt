package com.craftkal.alya.core.personality

import java.time.LocalDate
import kotlin.random.Random

enum class DayMood {
    MOOD_CERIA, MOOD_BIASA, MOOD_MELLOW, MOOD_MAGER
}

data class EmotionalDayProfile(
    val date: LocalDate,
    val baseMood: DayMood,
    val energyLevel: Int,            // 1-10
    val jajanCraving: String,
    val activityWish: String,
    val randomQuirk: String,
    val responseSpeedFeel: Float,    // 0.8-1.2
    val verbosityLevel: Int          // 1-3
)

object DayProfileGenerator {
    private val JAJAN_LIST = listOf("Cilok", "Boba", "Bakso", "Mie Ayam", "Seblak", "Nasi Goreng")
    private val ACTIVITY_LIST = listOf("Jalan ke Mall", "Nonton Film", "Main Game", "Rebahan", "Piknik")
    private val QUIRK_LIST = listOf("Lagi suka lagu lawas", "Lagi pengen dandan", "Lagi rajin bersih-bersih", "Lagi mager total")

    fun generate(date: LocalDate): EmotionalDayProfile {
        val seed = date.toEpochDay()
        val rng = Random(seed)
        
        return EmotionalDayProfile(
            date = date,
            baseMood = DayMood.values()[rng.nextInt(DayMood.values().size)],
            energyLevel = rng.nextInt(3, 11),
            jajanCraving = JAJAN_LIST[rng.nextInt(JAJAN_LIST.size)],
            activityWish = ACTIVITY_LIST[rng.nextInt(ACTIVITY_LIST.size)],
            randomQuirk = QUIRK_LIST[rng.nextInt(QUIRK_LIST.size)],
            responseSpeedFeel = 0.8f + rng.nextFloat() * 0.4f,
            verbosityLevel = rng.nextInt(1, 4)
        )
    }
}
