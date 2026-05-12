package com.craftkal.alya.core.engine

data class EntityResult(
    val time: String? = null,
    val food: String? = null,
    val location: String? = null
)

class EntityExtractor {
    private val foodList = listOf("cilok", "boba", "bakso", "mie ayam", "seblak", "nasi goreng")
    private val locationList = listOf("mall", "taman", "cafe", "rumah", "sekolah")
    
    fun extract(text: String): EntityResult {
        var food: String? = null
        var location: String? = null
        var time: String? = null
        
        foodList.forEach { if (text.contains(it)) food = it }
        locationList.forEach { if (text.contains(it)) location = it }
        
        if (text.contains("pagi")) time = "PAGI"
        else if (text.contains("siang")) time = "SIANG"
        else if (text.contains("sore")) time = "SORE"
        else if (text.contains("malam")) time = "MALAM"
        
        return EntityResult(time, food, location)
    }
}
