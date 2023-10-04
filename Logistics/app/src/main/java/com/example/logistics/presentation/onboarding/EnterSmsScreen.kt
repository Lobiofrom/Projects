package com.example.logistics.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.logistics.R
import com.example.logistics.utils.PhoneField

@Composable
fun EnterSms(
    phoneNumber: String,
    onClick: () -> Unit,
    onButtonClick: () -> Unit
) {
    var sms by rememberSaveable {
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
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .padding(top = 64.dp, start = 16.dp, bottom = 16.dp)
                    .paint(painterResource(id = R.drawable.img_3))
            ){}
            Text(
                text = "Код был отправлен на номер телефона \n+7$phoneNumber",
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            PhoneField(
                phone = sms,
                mask = "0-0-0-0-0-0",
                onPhoneChanged = {
                    sms = it
                },
                label = {
                        Text(text = "СМС")
                },
                modifier = Modifier
                    .width(328.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 96.dp)
            )
            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .width(328.dp)
                    .align(Alignment.CenterHorizontally),
                enabled = sms.length == 6
            ) {
                Text(text = "Продолжить")
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewSms() {
//    LogisticsTheme {
//        EnterSms()
//    }
//}
