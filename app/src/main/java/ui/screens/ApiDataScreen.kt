package com.example.a207419_lishangyu_izwan_project1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a207419_lishangyu_izwan_project1.api.RetrofitInstance
import com.example.a207419_lishangyu_izwan_project1.viewmodel.Project1ViewModel

@Composable
fun ApiDataScreen(
    viewModel: Project1ViewModel,
    goBackHome: () -> Unit
) {
    var resultText by remember {
        mutableStateOf("Loading healthy advice...")
    }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitInstance.api.getAdvice()

            resultText = response.slip.advice

        } catch (e: Exception) {
            resultText =
                "Failed to load online advice. Please check your internet connection."
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Daily Healthy Advice",
            style = MaterialTheme.typography.headlineSmall
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(resultText)

                Text(
                    text = "This advice is retrieved from an online API service."
                )
            }
        }

        Button(
            onClick = goBackHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back Home")
        }
    }
}