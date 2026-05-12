package com.craftkal.alya.core.memory

import java.util.ArrayDeque

enum class MoodState {
    NORMAL, SENANG, NGAMBEK, NGANTUK, MALES
}

data class Message(val text: String, val isUser: Boolean, val timestamp: Long = System.currentTimeMillis())

class SessionMemory {
    val recentMessages = ArrayDeque<Message>(5)
    var currentTopic: String? = null
    val repeatTracker = HashMap<String, Int>()
    var moodState: MoodState = MoodState.NORMAL
    val topicChain = mutableListOf<String>()
    
    fun addMessage(message: Message) {
        if (recentMessages.size >= 5) {
            recentMessages.removeFirst()
        }
        recentMessages.addLast(message)
    }
}
