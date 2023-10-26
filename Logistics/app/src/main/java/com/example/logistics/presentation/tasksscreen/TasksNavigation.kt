package com.example.logistics.presentation.tasksscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.logistics.data.Task
import com.example.logistics.presentation.viewmodel.MyViewModel

@Composable
fun TasksNavigation(
    viewModel: MyViewModel,
    changeModifier: () -> Unit,
    changeModifier2: () -> Unit,

    ) {
    val navController2 = rememberNavController()
    var tasks by remember {
        mutableStateOf(emptyList<Task>())
    }

    LaunchedEffect(Unit) {
        viewModel.items.collect {
            tasks = it
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        NavHost(navController = navController2, startDestination = "tasks") {
            composable(route = "tasks") {
                TasksScreen(
                    tasks = tasks,
                    navController = navController2,
                    changeModifier = changeModifier
                )
            }
            composable(
                route = "taskDetails/{title}",
                arguments = listOf(navArgument("title") { type = NavType.StringType })
            ) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title")
                val task = tasks.find { task ->
                    task.title == title
                }
                if (task != null) {
                    TaskDetails(
                        onClick = {
                            navController2.navigate("tasks")
                            changeModifier2()
                        },
                        task = task
                    ) {

                    }
                }
            }
        }
    }
}