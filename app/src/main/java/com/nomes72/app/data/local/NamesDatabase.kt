package com.nomes72.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nomes72.app.data.local.dao.SacredNameDao
import com.nomes72.app.data.local.entity.SacredNameEntity

@Database(
    entities = [SacredNameEntity::class],
    version = 1,
    exportSchema = true
)
abstract class NamesDatabase : RoomDatabase() {
    abstract fun sacredNameDao(): SacredNameDao
}