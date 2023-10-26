package com.example.logistics.presentation.tasksscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.logistics.data.Task

@Composable
fun TasksScreen(
    tasks: List<Task>,
    navController: NavController,
    changeModifier: () -> Unit
) {
    Column(
        modifier = Modifier.padding(bottom = 46.dp)
    ) {
        Text(
            text = "Задания",
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn {
            items(tasks) { receivedTask ->
                TaskItem(
                    task = receivedTask,
                    onButtonClick = {
                        navController.navigate("taskDetails/${receivedTask.title}")
                        changeModifier()
                    }
                )
            }
        }
    }
}