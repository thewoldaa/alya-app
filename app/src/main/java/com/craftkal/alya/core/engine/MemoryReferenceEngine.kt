package com.craftkal.alya.core.engine

import com.craftkal.alya.data.db.entities.MemoryCrystal
import com.craftkal.alya.data.db.entities.CrystalType
import kotlin.random.Random

class MemoryReferenceEngine {

    fun shouldReference(crystals: List<MemoryCrystal>): MemoryCrystal? {
        if (crystals.isEmpty()) return null
        // 15% chance to reference a memory
        return if (Random.nextFloat() < 0.15f) {
            crystals.random()
        } else null
    }

    fun buildReferencePhrase(crystal: MemoryCrystal): String {
        return when (crystal.type) {
            CrystalType.USER_NAME -> "{USER_NAME}! aku inget loh nama kamu itu ${crystal.content} hehe"
            CrystalType.FAVORITE_FOOD -> "eh kamu kan suka ${crystal.content} ya? aku jadi pengen juga ih 😋"
            CrystalType.FAVORITE_PLACE -> "kapan-kapan ajak aku ke ${crystal.content} dong, kan kamu suka kesana"
            else -> "aku inget sesuatu tentang kamu..."
        }
    }
}
