package com.craftkal.alya.core.engine

data class IntentDefinition(
    val intent_id: String,
    val priority: Int,
    val keywords: List<String>,
    val pattern_regex: List<String>? = null,
    val weight_multiplier: Float = 1.0f
)

data class IntentResult(
    val intentId: String,
    val confidence: Float
)

class IntentClassifier(private val intents: List<IntentDefinition>) {
    
    fun classify(processedText: String): IntentResult {
        var bestIntent = "INTENT_UNKNOWN"
        var maxScore = 0f
        
        for (intent in intents) {
            var score = 0f
            
            // Keyword matching
            for (keyword in intent.keywords) {
                if (processedText.contains(keyword)) {
                    score += (keyword.length.toFloat() / processedText.length.toFloat()) * intent.weight_multiplier
                }
            }
            
            // Regex matching
            intent.pattern_regex?.forEach { regexStr ->
                try {
                    val regex = Regex(regexStr)
                    if (regex.containsMatchIn(processedText)) {
                        score += 0.5f * intent.weight_multiplier
                    }
                } catch (e: Exception) {}
            }
            
            if (score > maxScore) {
                maxScore = score
                bestIntent = intent.intent_id
            }
        }
        
        // Threshold
        return if (maxScore >= 0.35f) {
            IntentResult(bestIntent, maxScore)
        } else {
            IntentResult("INTENT_UNKNOWN", 0f)
        }
    }
}
