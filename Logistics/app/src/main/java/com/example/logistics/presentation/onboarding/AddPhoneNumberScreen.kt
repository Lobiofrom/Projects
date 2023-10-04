package com.example.logistics.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.logistics.R
import com.example.logistics.utils.PhoneField

@Composable
fun AddPhoneNumber(
    onContinueClick: (String) -> Unit
) {
    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = null,
                modifier = Modifier.padding(top = 64.dp, start = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.img_2),
                contentDescription = null,
                modifier = Modifier.padding(top = 64.dp, start = 16.dp, bottom = 96.dp)
            )
            PhoneField(
                phone = phoneNumber,
                mask = "+7 (000) 000-00-00",
                onPhoneChanged = {
                    phoneNumber = it
                },
                label = {
                        Text(text = "номер телефона")
                },
                modifier = Modifier
                    .width(328.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 96.dp)
            )
            Button(
                onClick = {
                    onContinueClick(phoneNumber)
                },
                modifier = Modifier
                    .width(328.dp)
                    .align(Alignment.CenterHorizontally),
                enabled = phoneNumber.length == 10
                ) {
                Text(text = "Продолжить")
            }
        }
    }
}

//@Preview
//@Composable
//fun Preview() {
//    LogisticsTheme {
//        AddPhoneNumber()
//    }
//}