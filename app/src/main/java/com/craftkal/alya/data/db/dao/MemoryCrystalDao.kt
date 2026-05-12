package com.craftkal.alya.data.db.dao

import androidx.room.*
import com.craftkal.alya.data.db.entities.MemoryCrystal
import com.craftkal.alya.data.db.entities.CrystalType
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryCrystalDao {
    @Query("SELECT * FROM memory_crystals")
    fun getAllCrystals(): Flow<List<MemoryCrystal>>

    @Query("SELECT * FROM memory_crystals WHERE type = :type")
    suspend fun getCrystalsByType(type: CrystalType): List<MemoryCrystal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrystal(crystal: MemoryCrystal)

    @Update
    suspend fun updateCrystal(crystal: MemoryCrystal)

    @Delete
    suspend fun deleteCrystal(crystal: MemoryCrystal)
}
