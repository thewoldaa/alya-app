package com.craftkal.alya.core.engine

import com.craftkal.alya.core.memory.SessionMemory
import com.craftkal.alya.core.memory.MoodState
import kotlinx.coroutines.delay

data class AlyaResponse(
    val text: String,
    val intentId: String,
    val mood: String
)

class AlyaBrain(
    private val preProcessor: TextPreProcessor,
    private val intentClassifier: IntentClassifier,
    private val entityExtractor: EntityExtractor,
    private val ruleEngine: RuleEngine,
    private val responseSelector: ResponseSelector,
    private val sessionMemory: SessionMemory,
    private val dayProfile: com.craftkal.alya.core.personality.EmotionalDayProfile
) {
    suspend fun processInput(userInput: String): AlyaResponse {
        // Normalization
        val processed = preProcessor.process(userInput)
        
        // Extraction
        val entities = entityExtractor.extract(processed)
        
        // Intent
        val intentResult = intentClassifier.classify(processed)
        
        // Rules -> Target Pool
        val targetPool = ruleEngine.applyRules(intentResult.intentId, entities)
        
        // Selection with Day Mood influence
        val currentMood = dayProfile.baseMood.name
        val rawResponse = responseSelector.select(targetPool, currentMood)
        
        // Post-processing (Placeholder injection)
        val finalResponse = injectPlaceholders(rawResponse, entities)
            .replace("{JAJAN_CRAVING}", dayProfile.jajanCraving)
            .replace("{ACTIVITY_WISH}", dayProfile.activityWish)
        
        // Update Memory
        sessionMemory.currentTopic = intentResult.intentId
        
        // Dynamic delay based on responseSpeedFeel
        val thinkDelay = (400 * dayProfile.responseSpeedFeel).toLong()
        delay(thinkDelay)
        
        return AlyaResponse(finalResponse, intentResult.intentId, currentMood)
    }
    
    private fun injectPlaceholders(text: String, entities: EntityResult): String {
        return text.replace("{WAKTU}", entities.time ?: "sekarang")
            .replace("{NAMA_MAKANAN}", entities.food ?: "jajanan")
            .replace("{LOKASI}", entities.location ?: "sana")
            .replace("{USER_NAME}", "Kamu")
    }
}
