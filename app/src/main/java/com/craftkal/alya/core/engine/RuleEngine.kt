package com.craftkal.alya.core.engine

import com.craftkal.alya.core.memory.SessionMemory

class RuleEngine(private val sessionMemory: SessionMemory) {
    
    fun applyRules(intentId: String, entities: EntityResult): String {
        return when (intentId) {
            "INTENT_PERINTAH_TIDUR" -> {
                val count = sessionMemory.repeatTracker[intentId] ?: 0
                val poolId = when {
                    count == 0 -> "TIDUR_TOLAK_PERTAMA"
                    count == 1 -> "TIDUR_MULAI_NURUT"
                    else -> "TIDUR_NURUT_SEPENUHNYA"
                }
                sessionMemory.repeatTracker[intentId] = count + 1
                poolId
            }
            "INTENT_SAPAAN" -> {
                if (entities.time != null) {
                    "SAPAAN_${entities.time}"
                } else {
                    "SAPAAN_UMUM"
                }
            }
            "INTENT_PERINTAH_MAKAN" -> {
                if (entities.food != null && isSayur(entities.food)) {
                    "TOLAK_SAYUR_KERAS"
                } else if (entities.food != null && isJajan(entities.food)) {
                    "SEMANGAT_JAJAN"
                } else {
                    "MAKAN_NETRAL"
                }
            }
            "INTENT_AJAKAN_JALAN" -> "AJAKAN_JALAN_ANTUSIAS"
            "INTENT_PUJIAN" -> "PUJIAN_RESPONSE"
            "INTENT_NGAMBEK" -> "NGAMBEK"
            else -> intentId.replace("INTENT_", "")
        }
    }
    
    private fun isSayur(food: String): Boolean {
        return listOf("bayam", "kangkung", "wortel", "brokoli", "sawi").contains(food.lowercase())
    }
    
    private fun isJajan(food: String): Boolean {
        return listOf("cilok", "boba", "snack", "kue", "roti", "coklat", "es krim").contains(food.lowercase())
    }
}
