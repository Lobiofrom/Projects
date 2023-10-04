package com.example.logistics.presentation.tasksscreen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logistics.R
import com.example.logistics.data.Task
import com.example.logistics.ui.theme.LogisticsTheme
import com.example.logistics.utils.getFileNameFromContentUri
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList

@Composable
fun AttachDocuments(
    task: Task,
    onIconClick: () -> Unit,
    onButtonClick: () -> Unit
) {
    var listToDisplay by remember {
        mutableStateOf(mutableStateListOf<String>())
    }

    listToDisplay = SnapshotStateList<String>().apply {
        addAll(task.photoList)
    }

    val context = LocalContext.current

    val getContent =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            val fileName = getFileNameFromContentUri(it, context)
            task.photoList.add(fileName)
            listToDisplay.add(fileName)
        }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            IconButton(
                onClick = onIconClick,
                modifier = Modifier
                    .paint(painter = painterResource(id = R.drawable.img_9))
            ) {}
            Image(
                painter = painterResource(id = R.drawable.img_10),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                items(listToDisplay) {
                    ItemPhotoName(text = it) {
                        listToDisplay.remove(it)
                        task.photoList.remove(it)
                    }
                }
            }
            Button(
                onClick = {
                    getContent.launch("image/jpg")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF4F6F9)
                ),
                modifier = Modifier
                    .width(296.dp)
                    .padding(top = 200.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_11),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(text = "Добавить фото", color = Color.Black)
            }
            Button(
                onClick = {
                    onButtonClick()
                    task.isAccepted = true
                },
                modifier = Modifier
                    .width(296.dp)
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B2D35),
                ),
                enabled = listToDisplay.isNotEmpty()
            ) {
                Text(text = "Завершить заказ", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun PreviewTaskDetails() {
    LogisticsTheme {
        //AttachDocuments()
    }
}