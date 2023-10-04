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
import com.example.logistics.data.Task
import com.example.logistics.presentation.chatscreen.ChatScreen
import com.example.logistics.presentation.profilescreen.ProfileScreen
import com.example.logistics.presentation.schedulescreen.ScheduleScreen
import com.example.logistics.presentation.tasksscreen.TasksScreen
import com.example.logistics.presentation.viewmodel.MyViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: MyViewModel
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
        NavHost(navController = navController, startDestination = "tasks") {
            composable(route = "tasks") {
                TasksScreen(
                    tasks = tasks
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