package com.craftkal.alya.core.engine

import com.craftkal.alya.data.db.entities.MemoryCrystal
import com.craftkal.alya.data.db.entities.CrystalType
import java.util.regex.Pattern

class MemoryExtractionEngine {
    
    fun extract(input: String): List<MemoryCrystal> {
        val crystals = mutableListOf<MemoryCrystal>()
        
        // Name pattern
        val namePattern = Pattern.compile("(nama aku|panggil aku|nama saya) (\\w+)", Pattern.CASE_INSENSITIVE)
        val nameMatcher = namePattern.matcher(input)
        if (nameMatcher.find()) {
            crystals.add(MemoryCrystal(
                type = CrystalType.USER_NAME,
                content = nameMatcher.group(2) ?: "",
                emotionTag = "IMPORTANT",
                firstMentionedAt = System.currentTimeMillis()
            ))
        }

        // Favorite Food pattern
        val foodPattern = Pattern.compile("(aku suka|favorit aku|makanan kesukaan) (\\w+)", Pattern.CASE_INSENSITIVE)
        val foodMatcher = foodPattern.matcher(input)
        if (foodMatcher.find()) {
            crystals.add(MemoryCrystal(
                type = CrystalType.FAVORITE_FOOD,
                content = foodMatcher.group(2) ?: "",
                emotionTag = "HAPPY",
                firstMentionedAt = System.currentTimeMillis()
            ))
        }

        return crystals
    }
}
