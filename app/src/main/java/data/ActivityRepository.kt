package com.example.a207419_lishangyu_izwan_project1.data

class ActivityRepository(
    private val dao: ActivityDao
) {

    suspend fun insertActivity(
        title: String,
        steps: Int,
        calories: Double
    ) {
        dao.insertActivity(
            ActivityEntity(
                title = title,
                steps = steps,
                calories = calories
            )
        )
    }

    suspend fun getAllActivities(): List<ActivityEntity> {
        return dao.getAllActivities()
    }
}