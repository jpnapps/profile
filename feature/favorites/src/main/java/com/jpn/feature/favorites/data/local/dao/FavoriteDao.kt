package com.jpn.feature.favorites.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jpn.feature.favorites.data.local.entity.FavoriteItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_items")
    fun getAllFavorites(): Flow<List<FavoriteItemEntity>>

    @Insert
    suspend fun insertFavorite(item: FavoriteItemEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteItemEntity)
}