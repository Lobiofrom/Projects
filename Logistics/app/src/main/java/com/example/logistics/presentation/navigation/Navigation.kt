package com.example.logistics.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logistics.data.Task
import com.example.logistics.presentation.chatscreen.ChatScreen
import com.example.logistics.presentation.profilescreen.ProfileScreen
import com.example.logistics.presentation.schedulescreen.ScheduleScreen
import com.example.logistics.presentation.tasksscreen.TasksNavigation
import com.example.logistics.presentation.viewmodel.MyViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: MyViewModel,
    changeModifier: () -> Unit,
    changeModifier2: () -> Unit
) {
    var tasks by remember {
        mutableStateOf(emptyList<Task>())
    }

    LaunchedEffect(Unit) {
        viewModel.items.collect {
            tasks = it
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(navController = navController, startDestination = "tasksNavigation") {
            composable(route = "tasksNavigation") {
                TasksNavigation(
                    viewModel = viewModel,
                    changeModifier = changeModifier,
                    changeModifier2 = changeModifier2
                )
            }
            composable(route = "schedule") {
                ScheduleScreen()
            }
            composable(route = "chat") {
                ChatScreen(viewModel)
            }
            composable(route = "profile") {
                ProfileScreen()
            }
        }
    }
}