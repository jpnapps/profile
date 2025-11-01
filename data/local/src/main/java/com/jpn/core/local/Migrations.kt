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

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
    }
}
val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add missing columns with default values
        database.execSQL("ALTER TABLE routine ADD COLUMN date INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE routine ADD COLUMN goal INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE routine ADD COLUMN name TEXT NOT NULL DEFAULT 'undefined'")
        database.execSQL("ALTER TABLE routine ADD COLUMN progress INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE routine ADD COLUMN subType INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE routine ADD COLUMN step INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE routine ADD COLUMN type INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE routine ADD COLUMN updatedAt INTEGER NOT NULL DEFAULT 0")
        // If id is missing, primary key column
        database.execSQL("ALTER TABLE routine ADD COLUMN id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT")
    }
}
