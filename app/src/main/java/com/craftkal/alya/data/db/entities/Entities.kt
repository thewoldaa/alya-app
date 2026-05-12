package com.craftkal.alya.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val text: String,
    val isUser: Boolean,
    val timestamp: Long
)

@Entity(tableName = "user_preferences")
data class UserPreferenceEntity(
    @PrimaryKey val userId: String = "default",
    val userName: String? = null,
    val favoriteFood: String? = null, // Store as JSON string or comma-separated
    val accentColor: Int = 0
)
