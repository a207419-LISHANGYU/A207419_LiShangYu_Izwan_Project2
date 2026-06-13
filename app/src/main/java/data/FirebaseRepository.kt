package com.example.a207419_lishangyu_izwan_project1.data

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    private val db = FirebaseFirestore.getInstance()

    fun uploadActivity(
        title: String,
        steps: Int,
        calories: Double
    ) {
        val activityData = hashMapOf(
            "title" to title,
            "steps" to steps,
            "calories" to calories,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("activities")
            .add(activityData)
    }
}