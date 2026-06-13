package com.example.a207419_lishangyu_izwan_project1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ActivityDao {

    @Insert
    suspend fun insertActivity(activity: ActivityEntity)

    @Query("SELECT * FROM activities")
    suspend fun getAllActivities(): List<ActivityEntity>
}