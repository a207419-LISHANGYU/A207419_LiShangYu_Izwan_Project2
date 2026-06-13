package com.example.a207419_lishangyu_izwan_project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.a207419_lishangyu_izwan_project1.data.ActivityDatabase
import com.example.a207419_lishangyu_izwan_project1.data.ActivityRepository
import com.example.a207419_lishangyu_izwan_project1.ui.screens.*
import com.example.a207419_lishangyu_izwan_project1.ui.theme.A207419_LiShangYu_Izwan_Project1Theme
import com.example.a207419_lishangyu_izwan_project1.viewmodel.Project1ViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddActivity : Screen("add_activity")
    object ActivityResult : Screen("activity_result")
    object SummaryList : Screen("summary_list")
    object ProfileGoal : Screen("profile_goal")
    object ApiData : Screen("api_data")
    object Sensor : Screen("sensor")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A207419_LiShangYu_Izwan_Project1Theme {
                Project1App()
            }
        }
    }
}

@Composable
fun Project1App(viewModel: Project1ViewModel = viewModel()) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val database = remember {
        Room.databaseBuilder(
            context,
            ActivityDatabase::class.java,
            "stepbloom_database"
        ).build()
    }

    val repository = remember {
        ActivityRepository(database.activityDao())
    }

    LaunchedEffect(Unit) {
        viewModel.setRepository(repository)
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                goToAddActivity = { navController.navigate(Screen.AddActivity.route) },
                goToResult = { navController.navigate(Screen.ActivityResult.route) },
                goToSummary = { navController.navigate(Screen.SummaryList.route) },
                goToProfile = { navController.navigate(Screen.ProfileGoal.route) },
                goToApiData = { navController.navigate(Screen.ApiData.route) },
                goToSensor = { navController.navigate(Screen.Sensor.route) }
            )
        }

        composable(Screen.AddActivity.route) {
            AddActivityScreen(
                viewModel = viewModel,
                goToResult = { navController.navigate(Screen.ActivityResult.route) }
            )
        }

        composable(Screen.ActivityResult.route) {
            ActivityResultScreen(
                viewModel = viewModel,
                goToSummary = { navController.navigate(Screen.SummaryList.route) },
                goBackHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Screen.SummaryList.route) {
            SummaryListScreen(
                viewModel = viewModel,
                goBackHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Screen.ProfileGoal.route) {
            ProfileGoalScreen(
                viewModel = viewModel,
                goBackHome = { navController.popBackStack() }
            )
        }

        composable(Screen.ApiData.route) {
            ApiDataScreen(
                viewModel = viewModel,
                goBackHome = { navController.navigate(Screen.Home.route) }
            )
        }

        composable(Screen.Sensor.route) {
            SensorScreen(
                viewModel = viewModel,
                goBackHome = { navController.navigate(Screen.Home.route) }
            )
        }
    }
}