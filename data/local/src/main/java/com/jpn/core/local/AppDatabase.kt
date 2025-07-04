package com.jpn.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jpn.core.local.converter.RefLinksConverter
import com.jpn.core.local.dao.FavoriteDao
import com.jpn.core.local.dao.NoteDao
import com.jpn.core.local.dao.PwdDao
import com.jpn.core.local.entity.FavoriteItemEntity
import com.jpn.core.local.entity.NoteEntity
import com.jpn.core.local.entity.PwdEntity


@Database(entities = [FavoriteItemEntity::class, NoteEntity::class, PwdEntity::class], version = 1)
@TypeConverters(RefLinksConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun noteDao(): NoteDao
    abstract fun pwdDao(): PwdDao
}