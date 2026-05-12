package com.craftkal.alya.core.engine

import kotlin.random.Random

data class ResponseVariation(
    val id: String,
    val text: String,
    val weight: Int,
    val mood: String
)

data class ResponsePool(
    val pool_id: String,
    val responses: List<ResponseVariation>
)

class ResponseSelector(private val pools: Map<String, ResponsePool>) {
    
    fun select(poolId: String, mood: String): String {
        val pool = pools[poolId] ?: pools["FALLBACK_BINGUNG"] ?: return "..."
        
        val filtered = pool.responses.filter { it.mood == mood || it.mood == "NORMAL" }
        if (filtered.isEmpty()) return pool.responses.first().text
        
        val totalWeight = filtered.sumOf { it.weight }
        var randomValue = Random.nextInt(totalWeight)
        
        for (variation in filtered) {
            randomValue -= variation.weight
            if (randomValue < 0) {
                return variation.text
            }
        }
        
        return filtered.last().text
    }
}
