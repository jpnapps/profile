package com.jpn.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_items")
    fun getAllFavorites(): Flow<List<com.jpn.core.local.entity.FavoriteItemEntity>>

    @Insert
    suspend fun insertFavorite(item: com.jpn.core.local.entity.FavoriteItemEntity)

    @Delete
    suspend fun deleteFavorite(favorite: com.jpn.core.local.entity.FavoriteItemEntity)
}