package com.example.a207419_lishangyu_izwan_project1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a207419_lishangyu_izwan_project1.data.ActivityItem
import com.example.a207419_lishangyu_izwan_project1.data.ActivityRepository
import com.example.a207419_lishangyu_izwan_project1.data.FirebaseRepository
import kotlinx.coroutines.launch

class Project1ViewModel : ViewModel() {

    private var nextId by mutableIntStateOf(1)

    var userName by mutableStateOf("A207419")
        private set

    var goalSteps by mutableStateOf(6000)
        private set

    val activityList = mutableStateListOf<ActivityItem>()

    private var repository: ActivityRepository? = null
    private val firebaseRepository = FirebaseRepository()

    fun setRepository(repo: ActivityRepository) {
        repository = repo
        loadActivitiesFromRoom()
    }

    fun updateUserName(newName: String) {
        userName = newName
    }

    fun updateGoal(newGoal: Int) {
        goalSteps = newGoal
    }

    fun addActivity(title: String, steps: Int) {
        val calories = steps * 0.04

        val newActivity = ActivityItem(
            id = nextId++,
            title = title,
            steps = steps,
            calories = calories
        )

        activityList.add(newActivity)

        viewModelScope.launch {
            repository?.insertActivity(
                title = title,
                steps = steps,
                calories = calories
            )

            firebaseRepository.uploadActivity(
                title = title,
                steps = steps,
                calories = calories
            )
        }
    }

    private fun loadActivitiesFromRoom() {
        viewModelScope.launch {
            val savedActivities = repository?.getAllActivities() ?: emptyList()

            activityList.clear()
            activityList.addAll(
                savedActivities.map {
                    ActivityItem(
                        id = it.id,
                        title = it.title,
                        steps = it.steps,
                        calories = it.calories
                    )
                }
            )

            nextId = (activityList.maxOfOrNull { it.id } ?: 0) + 1
        }
    }

    fun getLatestActivity(): ActivityItem? {
        return activityList.lastOrNull()
    }
}