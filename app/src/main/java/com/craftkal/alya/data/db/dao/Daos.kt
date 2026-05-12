package com.craftkal.alya.data.db.dao

import androidx.room.*
import com.craftkal.alya.data.db.entities.MessageEntity
import com.craftkal.alya.data.db.entities.UserPreferenceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<MessageEntity>>

    @Insert
    suspend fun insertMessage(message: MessageEntity)

    @Query("DELETE FROM messages")
    suspend fun clearHistory()
}

@Dao
interface UserPreferenceDao {
    @Query("SELECT * FROM user_preferences WHERE userId = :id")
    suspend fun getPreferences(id: String = "default"): UserPreferenceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePreferences(prefs: UserPreferenceEntity)
}
