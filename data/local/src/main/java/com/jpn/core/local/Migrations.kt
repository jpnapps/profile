package com.jpn.core.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// Define your migration(s)
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE pwd ADD COLUMN fieldLabel TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE pwd ADD COLUMN secureLabel TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE pwd ADD COLUMN fieldValue TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE pwd ADD COLUMN secureValue TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE pwd ADD COLUMN fieldLock INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE pwd ADD COLUMN secureLock INTEGER NOT NULL DEFAULT 0")
    }

}
