package com.craftkal.alya.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "diary_entries")
data class DiaryEntry(
    @PrimaryKey val date: String, // ISO date string
    val content: String,
    val moodEmoji: String,
    val totalMessages: Int
)

class DiaryGenerator {
    fun generate(date: LocalDate, messages: Int, mood: String): String {
        return "Hari ini aku merasa $mood. Tadi ngobrol lumayan banyak, ada $messages pesan. Seru banget deh!"
    }
}
