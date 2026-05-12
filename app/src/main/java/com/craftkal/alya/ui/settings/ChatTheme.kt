package com.craftkal.alya.ui.settings

data class ChatTheme(
    val id: String,
    val name: String,
    val isDark: Boolean,
    val backgroundColor: Int,
    val alyaBubbleColor: Int,
    val userBubbleColor: Int,
    val alyaTextColor: Int,
    val userTextColor: Int,
    val fontSize: Float = 14f
)

object ThemePresets {
    val CLASSIC = ChatTheme(
        id = "classic",
        name = "Alya Classic",
        isDark = false,
        backgroundColor = 0xFFFFFFFF.toInt(),
        alyaBubbleColor = 0xFFF1F0F0.toInt(),
        userBubbleColor = 0xFFDCF8C6.toInt(),
        alyaTextColor = 0xFF000000.toInt(),
        userTextColor = 0xFF000000.toInt()
    )
    
    val MIDNIGHT = ChatTheme(
        id = "midnight",
        name = "Midnight Alya",
        isDark = true,
        backgroundColor = 0xFF0D0D0D.toInt(),
        alyaBubbleColor = 0xFF2C2C2C.toInt(),
        userBubbleColor = 0xFF1B3D2F.toInt(),
        alyaTextColor = 0xFFFFFFFF.toInt(),
        userTextColor = 0xFFFFFFFF.toInt()
    )
}
