package com.example.a207419_lishangyu_izwan_project1.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ActivityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ActivityDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDao
}