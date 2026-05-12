package com.craftkal.alya.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.craftkal.alya.data.db.dao.MessageDao
import com.craftkal.alya.data.db.dao.UserPreferenceDao
import com.craftkal.alya.data.db.entities.MessageEntity
import com.craftkal.alya.data.db.entities.UserPreferenceEntity

@Database(entities = [MessageEntity::class, UserPreferenceEntity::class, MemoryCrystal::class], version = 1)
abstract class AlyaDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun userPreferenceDao(): UserPreferenceDao
    abstract fun memoryCrystalDao(): com.craftkal.alya.data.db.dao.MemoryCrystalDao
}
