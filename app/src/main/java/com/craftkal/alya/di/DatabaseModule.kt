package com.craftkal.alya.di

import android.content.Context
import androidx.room.Room
import com.craftkal.alya.data.db.AlyaDatabase
import com.craftkal.alya.data.db.dao.MessageDao
import com.craftkal.alya.data.db.dao.UserPreferenceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AlyaDatabase {
        return Room.databaseBuilder(
            context,
            AlyaDatabase::class.java,
            "alya_database"
        ).build()
    }

    @Provides
    fun provideMessageDao(db: AlyaDatabase): MessageDao = db.messageDao()

    @Provides
    fun provideUserPreferenceDao(db: AlyaDatabase): UserPreferenceDao = db.userPreferenceDao()
}
