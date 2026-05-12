package com.craftkal.alya.core.engine

import java.util.Locale

class TextPreProcessor {
    fun process(text: String): String {
        return text.lowercase(Locale.getDefault())
            .replace(Regex("[^a-z0-9\\s]"), "") // Remove special characters
            .trim()
    }
    
    fun tokenize(text: String): List<String> {
        return process(text).split("\\s+".toRegex())
    }
}
