package com.craftkal.alya.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class CrystalType {
    USER_NAME, FAVORITE_FOOD, FAVORITE_PLACE, BIRTHDAY, HOBBY, PET_NAME, STORY_MOMENT, DISLIKE, DREAM, INSIDE_JOKE
}

@Entity(tableName = "memory_crystals")
data class MemoryCrystal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: CrystalType,
    val content: String,
    val emotionTag: String,         // HAPPY / FUNNY / SAD / IMPORTANT
    val firstMentionedAt: Long,
    val mentionCount: Int = 1,
    val lastReferenced: Long = 0,
    val userConfirmed: Boolean = false
)
