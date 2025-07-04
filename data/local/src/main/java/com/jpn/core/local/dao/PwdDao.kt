package com.jpn.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jpn.core.local.entity.PwdEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PwdDao {

    @Query("SELECT * FROM pwd")
    fun getAllPwds(): Flow<List<PwdEntity>>

    @Insert
    suspend fun insertPwd(item: PwdEntity)

    @Delete
    suspend fun deletePwd(item: PwdEntity)

    @Query("SELECT * FROM pwd WHERE id = :id")
    fun getPwdById(id:Int): Flow<PwdEntity>

    @Update
    suspend fun updatePwd(item: PwdEntity)
}