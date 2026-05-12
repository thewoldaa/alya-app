# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/builder/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools-proguard.html

# Add any project specific keep rules here:

# Room
-keep class androidx.room.** { *; }

# Hilt
-keep class dagger.hilt.** { *; }

# Gson
-keep class com.google.gson.** { *; }

# Alya Models
-keep class com.craftkal.alya.data.db.entities.** { *; }
-keep class com.craftkal.alya.core.personality.** { *; }
