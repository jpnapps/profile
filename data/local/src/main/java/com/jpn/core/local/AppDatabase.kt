package com.jpn.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jpn.core.local.dao.FavoriteDao
import com.jpn.core.local.entity.FavoriteItemEntity


@Database(entities = [FavoriteItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}