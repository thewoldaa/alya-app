package com.craftkal.alya.core.engine

import com.craftkal.alya.core.personality.EmotionalDayProfile
import kotlin.random.Random

class ProactiveConversationEngine {
    
    private val topics = listOf(
        "eh btw, tadi aku kepikiran pengen makan {JAJAN_CRAVING} banget, kamu suka ga?",
        "hmm... tiba-tiba nanya, kamu lebih suka hujan atau panas?",
        "ih aku lagi pengen banget {ACTIVITY_WISH}, kamu mau ikut ga?",
        "btw kamu punya makanan favorit ga? aku penasaran 😋",
        "hm... kamu kalau bosen biasanya ngapain?"
    )

    fun generateTopicInitiator(profile: EmotionalDayProfile): String {
        val raw = topics.random()
        return raw.replace("{JAJAN_CRAVING}", profile.jajanCraving)
                  .replace("{ACTIVITY_WISH}", profile.activityWish)
    }
}
