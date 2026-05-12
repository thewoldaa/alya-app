package com.craftkal.alya.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.craftkal.alya.data.db.dao.MessageDao
import com.craftkal.alya.data.db.dao.UserPreferenceDao
import com.craftkal.alya.data.db.dao.MemoryCrystalDao
import com.craftkal.alya.data.db.entities.MessageEntity
import com.craftkal.alya.data.db.entities.UserPreferenceEntity
import com.craftkal.alya.data.db.entities.MemoryCrystal
import com.craftkal.alya.data.db.entities.DiaryEntry

@Database(entities = [MessageEntity::class, UserPreferenceEntity::class, MemoryCrystal::class, DiaryEntry::class], version = 1)
abstract class AlyaDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun userPreferenceDao(): UserPreferenceDao
    abstract fun memoryCrystalDao(): MemoryCrystalDao
}
