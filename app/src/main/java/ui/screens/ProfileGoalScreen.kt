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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.a207419_lishangyu_izwan_project1.viewmodel.Project1ViewModel

@Composable
fun ProfileGoalScreen(
    viewModel: Project1ViewModel,
    goBackHome: () -> Unit
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("stepbloom_profile", Context.MODE_PRIVATE)

    var userName by remember {
        mutableStateOf(prefs.getString("userName", viewModel.userName) ?: viewModel.userName)
    }

    var goalSteps by remember {
        mutableStateOf(prefs.getInt("goalSteps", viewModel.goalSteps).toString())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Profile / Goal",
            style = MaterialTheme.typography.headlineLarge
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text("User Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = goalSteps,
                    onValueChange = { goalSteps = it },
                    label = { Text("Goal Steps") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        val goal = goalSteps.toIntOrNull() ?: 6000

                        prefs.edit()
                            .putString("userName", userName)
                            .putInt("goalSteps", goal)
                            .apply()

                        viewModel.updateUserName(userName)
                        viewModel.updateGoal(goal)

                        goBackHome()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Profile")
                }
            }
        }
    }
}

