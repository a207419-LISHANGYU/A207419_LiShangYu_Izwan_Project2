package com.example.a207419_lishangyu_izwan_project1.ui.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a207419_lishangyu_izwan_project1.viewmodel.Project1ViewModel

@Composable
fun ActivityResultScreen(
    viewModel: Project1ViewModel,
    goToSummary: () -> Unit,
    goBackHome: () -> Unit
) {
    val latestActivity = viewModel.getLatestActivity()
    val totalSteps = viewModel.activityList.sumOf { it.steps }
    val totalCalories = viewModel.activityList.sumOf { it.calories }
    val status = if (totalSteps >= viewModel.goalSteps) "Goal achieved" else "Goal not reached"
    val advice = if (totalSteps >= viewModel.goalSteps) {
        "Great job! You reached your goal."
    } else {
        "Keep moving to reach your goal."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Activity Result",
            style = MaterialTheme.typography.headlineLarge
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Latest Activity: ${latestActivity?.title ?: "No activity yet"}")
                Text("Latest Steps: ${latestActivity?.steps ?: 0}")
                Text("Latest Calories: %.1f".format(latestActivity?.calories ?: 0.0))
                Text("Total Steps: $totalSteps")
                Text("Total Calories: %.1f".format(totalCalories))
                Text("Status: $status")
                Text("Advice: $advice")
            }
        }

        Button(
            onClick = goToSummary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Summary List")
        }

        Button(
            onClick = goBackHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Home")
        }
    }
}

