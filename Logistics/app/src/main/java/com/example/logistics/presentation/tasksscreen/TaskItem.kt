package com.example.logistics.presentation.tasksscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logistics.data.Task

@Composable
fun TaskItem(
    task: Task,
    onButtonClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(328.dp)
            .height(294.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = task.title,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = 26.sp
            )
            if (task.isAccepted) {
                Text(
                    text = "Текущее задание",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp),
                    color = Color(0xFFB8C4DB)
                )
            }
            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B2D35)
                )
            ) {
                Text(text = "Посмотреть детали", color = Color.White)
            }
        }
    }
}

//val task = Task(
//    description = "Task 1",
//    isAccepted = true
//)

//@Preview
//@Composable
//fun PreviewTaskItem() {
//    LogisticsTheme {
//        TaskItem(task = task)
//    }
//}