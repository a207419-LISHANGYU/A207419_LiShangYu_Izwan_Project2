package com.example.a207419_lishangyu_izwan_project1.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.a207419_lishangyu_izwan_project1.viewmodel.Project1ViewModel

@Composable
fun HomeScreen(
    viewModel: Project1ViewModel,
    goToAddActivity: () -> Unit,
    goToResult: () -> Unit,
    goToSummary: () -> Unit,
    goToProfile: () -> Unit,
    goToApiData: () -> Unit,
    goToSensor: () -> Unit
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("stepbloom_profile", Context.MODE_PRIVATE)

    LaunchedEffect(Unit) {
        val savedName = prefs.getString("userName", viewModel.userName) ?: viewModel.userName
        val savedGoal = prefs.getInt("goalSteps", viewModel.goalSteps)

        viewModel.updateUserName(savedName)
        viewModel.updateGoal(savedGoal)
    }

    val latestActivity = viewModel.getLatestActivity()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "StepBloom",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "User: ${viewModel.userName}",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Goal Steps: ${viewModel.goalSteps}",
            style = MaterialTheme.typography.bodyLarge
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Total Activities: ${viewModel.activityList.size}")

                if (latestActivity == null) {
                    Text("Latest Activity: No activity yet")
                } else {
                    Text("Latest Activity: ${latestActivity.title}")
                    Text("Latest Steps: ${latestActivity.steps}")
                    Text("Latest Calories: %.1f".format(latestActivity.calories))
                }
            }
        }

        Button(
            onClick = goToAddActivity,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Activity")
        }

        Button(
            onClick = goToResult,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Result")
        }

        Button(
            onClick = goToSummary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Summary List")
        }

        Button(
            onClick = goToProfile,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Profile / Goal")
        }

        Button(
            onClick = goToApiData,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("API Data")
        }

        Button(
            onClick = goToSensor,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sensor Data")
        }
    }
}