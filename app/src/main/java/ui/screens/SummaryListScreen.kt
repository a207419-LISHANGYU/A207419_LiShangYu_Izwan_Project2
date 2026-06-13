package com.example.a207419_lishangyu_izwan_project1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun SummaryListScreen(
    viewModel: Project1ViewModel,
    goBackHome: () -> Unit
) {
    val totalSteps = viewModel.activityList.sumOf { it.steps }
    val totalCalories = viewModel.activityList.sumOf { it.calories }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Summary List",
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
                Text("Total Activities: ${viewModel.activityList.size}")
                Text("Total Steps: $totalSteps")
                Text("Total Calories: %.1f".format(totalCalories))
            }
        }

        if (viewModel.activityList.isEmpty()) {
            Text("No activity added yet")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewModel.activityList) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Title: ${item.title}")
                            Text("Steps: ${item.steps}")
                            Text("Calories: %.1f".format(item.calories))
                        }
                    }
                }
            }
        }

        Button(
            onClick = goBackHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Home")
        }
    }
}
