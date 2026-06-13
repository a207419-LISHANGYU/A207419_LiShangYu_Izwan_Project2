package com.example.a207419_lishangyu_izwan_project1.ui.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.a207419_lishangyu_izwan_project1.viewmodel.Project1ViewModel

@Composable
fun SensorScreen(
    viewModel: Project1ViewModel,
    goBackHome: () -> Unit
) {
    val context = LocalContext.current

    var xValue by remember { mutableStateOf(0f) }
    var yValue by remember { mutableStateOf(0f) }
    var zValue by remember { mutableStateOf(0f) }
    var status by remember { mutableStateOf("Waiting for sensor data...") }

    DisposableEffect(Unit) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event != null) {
                    xValue = event.values[0]
                    yValue = event.values[1]
                    zValue = event.values[2]

                    val movement = kotlin.math.abs(xValue) +
                            kotlin.math.abs(yValue) +
                            kotlin.math.abs(zValue)

                    status = if (movement > 20) {
                        "Strong movement detected"
                    } else {
                        "Normal movement"
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        if (accelerometer != null) {
            sensorManager.registerListener(
                listener,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } else {
            status = "Accelerometer not available"
        }

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Health Movement Sensor",
            style = MaterialTheme.typography.headlineSmall
        )

        Text("X value: %.2f".format(xValue))
        Text("Y value: %.2f".format(yValue))
        Text("Z value: %.2f".format(zValue))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = status)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "This sensor helps detect body movement for health activity tracking."
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