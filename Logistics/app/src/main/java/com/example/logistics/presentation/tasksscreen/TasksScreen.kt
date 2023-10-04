package com.example.logistics.presentation.tasksscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.logistics.data.Task

@Composable
fun TasksScreen(
    tasks: List<Task>,
) {

    var showTaskDetails by remember {
        mutableStateOf(false)
    }

    var task by remember {
        mutableStateOf<Task?>(null)
    }

    if (!showTaskDetails) {
        Column(
            modifier = Modifier.padding(bottom = 46.dp)
        ) {
            Text(
                text = "Задания",
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn {
                items(tasks) {
                    TaskItem(
                        task = it,
                        onButtonClick = {
                            showTaskDetails = true
                            task = it
                        }
                    )
                }
            }
        }
    } else {
        task?.let {
            TaskDetails(
                onClick = { showTaskDetails = false },
                task = it,
                onCompleteButtonClick = { showTaskDetails = false }
            )
        }
    }
}

//@Preview
//@Composable
//fun PreviewTask() {
//    LogisticsTheme {
//        TasksScreen()
//    }
//}