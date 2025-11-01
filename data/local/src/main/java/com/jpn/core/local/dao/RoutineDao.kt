package com.jpn.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jpn.core.local.entity.RoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {

    @Query("SELECT * FROM routine")
    fun getAllRoutines(): Flow<List<RoutineEntity>>

    @Query("SELECT * FROM routine")
    suspend fun getRoutines(): List<RoutineEntity>

    // --- Queries by type/subType ---
    @Query("SELECT * FROM routine WHERE type = :type AND date = :date")
    suspend fun getRoutinesByType2(type: Int, date: Long): List<RoutineEntity>

    @Query("SELECT * FROM routine WHERE type = :type AND date = :date")
    fun getRoutinesByType(type: Int, date: Long): Flow<List<RoutineEntity>>

    @Query("SELECT * FROM routine WHERE id = :id")
    fun getRoutineById(id: Int): Flow<RoutineEntity>


    // Generic add/update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutine(routine: RoutineEntity)

    @Update
    suspend fun updateRoutine(routine: RoutineEntity)

    @Delete
    suspend fun deleteRoutine(routine: RoutineEntity)


    @Query("SELECT * FROM routine WHERE type = :type AND subType = :subType AND date = :date LIMIT 1")
    suspend fun getRoutine(type: Int, subType: Int, date: Long): RoutineEntity?

    @Query("SELECT * FROM routine WHERE date = :date ")
    suspend fun getRoutinesToday(date: Long): List<RoutineEntity>

    // History
    @Query("SELECT * FROM routine WHERE type = :type ORDER BY date DESC")
    suspend fun getRoutineHistory(type: Int): List<RoutineEntity>
}

/*
    // Example: get Water today
    @Query("SELECT * FROM routine WHERE type = :healthType AND subType = :waterSubType AND date = :date LIMIT 1")
    suspend fun getWaterToday(
        healthType: Int = RoutineType.HEALTH,
        waterSubType: Int = RoutineSubType.WATER,
        date: Long
    ): RoutineEntity?
*/
