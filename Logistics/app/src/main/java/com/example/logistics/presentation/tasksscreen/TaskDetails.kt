package com.example.logistics.presentation.tasksscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logistics.R
import com.example.logistics.data.Task

@Composable
fun TaskDetails(
    onClick: () -> Unit,
    task: Task,
    onCompleteButtonClick: () -> Unit
) {

    var newTask by remember {
        mutableStateOf<Task?>(null)
    }

    newTask = task

    var showButtons by remember {
        mutableStateOf(true)
    }

    var showAddMistake by remember {
        mutableStateOf(false)
    }

    var showMistakesButtons by remember {
        mutableStateOf(false)
    }

    var showAttachDocuments by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (!showAddMistake) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 46.dp),
            ) {
                IconButton(
                    onClick = onClick,
                    modifier = Modifier.paint(painterResource(id = R.drawable.img_5))
                ) {}
                Text(
                    text = task.description,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 206.dp
                    ),
                    fontSize = 26.sp
                )
                Text(
                    text = "Правила Клиента",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 46.dp)
                )
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF4F6F9)
                    ),
                    modifier = Modifier
                        .width(296.dp)
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    enabled = showButtons
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_6),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text(text = "Скачать", color = Color.Black)
                }
                if (showButtons) {
                    if (!showMistakesButtons) {
                        Button(
                            onClick = {
                                showMistakesButtons = true
                            },
                            modifier = Modifier
                                .width(296.dp)
                                .padding(bottom = 16.dp)
                                .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2B2D35),
                            )
                        ) {
                            Text(text = "Принять", color = Color.White)
                        }
                    }
                    if (showMistakesButtons) {
                        Surface(
                            modifier = Modifier
                                .width(329.dp)
                                .height(78.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp),
                            color = Color(0xFFF4F6F9),
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                if (task.mistakes.isNotEmpty()) {
                                    Box(
                                        modifier = Modifier
                                            .padding(start = 16.dp)
                                            .width(19.dp)
                                            .align(Alignment.CenterStart)
                                            .background(color = Color.Red, shape = CircleShape)
                                    ) {
                                        Text(
                                            text = task.mistakes.size.toString(),
                                            modifier = Modifier
                                                .align(Alignment.Center),
                                            color = Color.White
                                        )
                                    }
                                }
                                Text(
                                    text = "Инцидент",
                                    modifier = Modifier
                                        .padding(start = 46.dp)
                                        .align(Alignment.CenterStart)
                                )
                                IconButton(
                                    onClick = { showAddMistake = true },
                                    modifier = Modifier
                                        .paint(painterResource(id = R.drawable.img_7))
                                        .align(Alignment.CenterEnd)
                                ) {}
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .width(329.dp)
                                .height(78.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp),
                            color = Color(0xFFF4F6F9),
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                if (task.mistakes.isNotEmpty()) {
                                    Box(
                                        modifier = Modifier
                                            .padding(start = 16.dp)
                                            .width(19.dp)
                                            .align(Alignment.CenterStart)
                                            .background(color = Color.Red, shape = CircleShape)
                                    ) {
                                        Text(
                                            text = task.mistakes.size.toString(),
                                            modifier = Modifier
                                                .align(Alignment.Center),
                                            color = Color.White
                                        )
                                    }
                                }
                                Text(
                                    text = "Ошибка",
                                    modifier = Modifier
                                        .padding(start = 46.dp)
                                        .align(Alignment.CenterStart)
                                )
                                IconButton(
                                    onClick = { showAddMistake = true },
                                    modifier = Modifier
                                        .paint(painterResource(id = R.drawable.img_7))
                                        .align(Alignment.CenterEnd)
                                ) {}
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .width(329.dp)
                                .height(78.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp),
                            color = Color(0xFFF4F6F9),
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = "Прикрепить документы",
                                    modifier = Modifier
                                        .padding(start = 46.dp)
                                        .align(Alignment.CenterStart)
                                )
                                IconButton(
                                    onClick = {
                                        showAttachDocuments = true
                                    },
                                    modifier = Modifier
                                        .paint(painterResource(id = R.drawable.img_7))
                                        .align(Alignment.CenterEnd)
                                ) {}
                            }
                        }
                    }
                    Button(
                        onClick = {
                            showButtons = false
                            task.mistakes.clear()
                            task.isAccepted = false
                        },
                        modifier = Modifier
                            .width(296.dp)
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF4F6F9)
                        )
                    ) {
                        Text(text = "Отказаться", color = Color.Black)
                    }
                }
            }
        } else {
            AddMistakes(
                onClick = { showAddMistake = false },
                task = newTask!!
            )
        }
        if (showAttachDocuments) {
            AttachDocuments(
                task = newTask!!,
                onIconClick = { showAttachDocuments = false },
                onButtonClick = onCompleteButtonClick
            )
        }
    }
}
//
//@Preview
//@Composable
//fun PreviewTaskDetails() {
//    LogisticsTheme {
//        TaskDetails()
//    }
//}