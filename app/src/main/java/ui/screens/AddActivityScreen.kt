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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a207419_lishangyu_izwan_project1.viewmodel.Project1ViewModel

@Composable
fun AddActivityScreen(
    viewModel: Project1ViewModel,
    goToResult: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Add Activity",
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
                    value = title,
                    onValueChange = {
                        title = it
                        errorMessage = ""
                    },
                    label = { Text("Activity Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = steps,
                    onValueChange = {
                        steps = it
                        errorMessage = ""
                    },
                    label = { Text("Steps") },
                    modifier = Modifier.fillMaxWidth()
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Button(
                    onClick = {
                        val stepValue = steps.toIntOrNull()

                        when {
                            title.isBlank() -> {
                                errorMessage = "Please enter activity title"
                            }
                            stepValue == null -> {
                                errorMessage = "Please enter valid steps"
                            }
                            stepValue <= 0 -> {
                                errorMessage = "Steps must be more than 0"
                            }
                            else -> {
                                viewModel.addActivity(
                                    title = title.trim(),
                                    steps = stepValue
                                )
                                title = ""
                                steps = ""
                                errorMessage = ""
                                goToResult()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Activity")
                }
            }
        }
    }
}

