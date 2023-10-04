package com.example.logistics.presentation.tasksscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
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
import androidx.compose.ui.unit.dp
import com.example.logistics.R
import com.example.logistics.data.Task

@Composable
fun AddMistakes(
    onClick: () -> Unit,
    task: Task
) {

    var selectedOption by remember {
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            IconButton(
                onClick = onClick,
                Modifier.paint(
                    painter = painterResource(
                        id = R.drawable.img_8
                    )
                )
            ) {}
            Text(
                text = "Выберите что произошло",
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            )
            RadioGroup(
                options = listOf("Проблема на адресе", "Проблема в пути", "Проблема на загрузке"),
                selectedOption = selectedOption,
                onOptionSelected = { newSelectedOption ->
                    selectedOption = newSelectedOption
                }
            )
            Button(
                onClick = {
                    onClick()
                    task.mistakes.add(selectedOption)
                },
                modifier = Modifier
                    .width(296.dp)
                    .padding(top = 100.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B2D35)
                )
            ) {
                Text(text = "Сохранить", color = Color.White)
            }
        }
    }
}

@Composable
fun RadioGroup(
    options: List<String>,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        options.forEachIndexed { index, option ->
            Row {
                RadioButton(
                    selected = index == selectedOption,
                    onClick = { onOptionSelected(index) },
                    colors = RadioButtonDefaults.colors(MaterialTheme.colorScheme.primary)
                )
                Text(
                    text = option,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewTaskDetails() {
//    LogisticsTheme {
//        AddMistake()
//    }
//}