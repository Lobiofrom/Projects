package com.example.hotels.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.domain.models.Booking
import com.example.domain.models.PassengerList
import com.example.hotels.R
import com.example.hotels.utils.PhoneField
import com.example.hotels.utils.validateEmail
import com.example.hotels.viewModels.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    navController: NavController,
    viewModel: BookingViewModel
) {
    var email by rememberSaveable { mutableStateOf("") }

    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }

    val interactionSource = remember { MutableInteractionSource() }

    var booking by remember {
        mutableStateOf<Booking?>(null)
    }

    var passengerList by remember {
        mutableStateOf(PassengerList())
    }

    LaunchedEffect(Unit) {
        viewModel.booking.collect {
            booking = it
        }
    }

    LaunchedEffect(Unit) {
        viewModel.passengerList.collect {
            passengerList = it
        }
    }
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.img_4), contentDescription = null,
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .padding(start = 10.dp)
                        .clickable { navController.popBackStack() }
                )
                Text(
                    text = "Бронирование",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        item {
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 5.dp,
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Text(
                            text = booking?.horating.toString(),
                            fontSize = 15.sp,
                            color = Color(0xFFFFA800),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        booking?.rating_name?.let {
                            Text(
                                text = it,
                                fontSize = 15.sp,
                                color = Color(0xFFFFA800),
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                    booking?.hotel_name?.let {
                        Text(
                            text = it, fontSize = 22.sp,
                            modifier = Modifier.padding(top = 6.dp, start = 16.dp),
                        )
                    }
                    booking?.let {
                        Text(
                            text = it.hotel_adress, fontSize = 14.sp, color = Color(0xFF0D72FF),
                            modifier = Modifier.padding(top = 6.dp, start = 16.dp, bottom = 6.dp),
                        )
                    }
                }
            }
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 5.dp,
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .fillMaxWidth()
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Вылет из", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        booking?.departure?.let {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 150.dp)
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = "Страна, город", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        booking?.arrival_country?.let {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 150.dp)
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Даты", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        Text(
                            text = "${booking?.tour_date_start} - ${booking?.tour_date_stop}",
                            fontSize = 16.sp, modifier = Modifier.padding(start = 150.dp)
                        )
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Кол-во ночей", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        Text(
                            text = booking?.number_of_nights.toString(),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 150.dp)
                        )
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = "Отель", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        booking?.hotel_name?.let {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 150.dp)
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Номер", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        booking?.room?.let {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 150.dp)
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Питание", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        booking?.nutrition?.let {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 150.dp, bottom = 6.dp)
                            )
                        }
                    }
                }
            }
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 5.dp,
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Column {
                    Text(
                        text = "Информация о покупателе",
                        fontSize = 22.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )
                    Column(modifier = Modifier.fillMaxWidth()) {
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
                                .width(343.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 16.dp, bottom = 6.dp),
                        )
                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            label = {
                                Text(
                                    text = "E-mail"
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = if (validateEmail(email)) Color(0xFFF6F6F9)
                                else Color(0xFFEB5757)
                            ),
                            modifier = Modifier
                                .width(343.dp)
                                .align(Alignment.CenterHorizontally)
                            //.padding(start = 16.dp)
                        )
                    }
                    Text(
                        text = "Эти данные никому не передаются. После оплаты мы вышли чек на указанный вами номер и почту",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 16.dp,
                            end = 16.dp,
                            bottom = 6.dp
                        ),
                        color = Color(0xFF828796)
                    )
                }
            }
        }

        items(passengerList.passengerList) { passenger ->
            ItemPassenger(passenger = passenger) { textField, tourist ->
                viewModel.onTextChange(textField, tourist)
            }
        }

        item {
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 5.dp,
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Добавить туриста",
                        style = TextStyle(
                            fontSize = 22.sp,
                        ),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.CenterStart)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = rememberRipple(
                                    bounded = true,
                                    //radius = 250.dp,
                                    color = Color.DarkGray
                                )
                            ) { viewModel.addPassenger() },
                    )
                }
            }
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 5.dp,
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Column {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 6.dp)
                    ) {
                        Text(
                            text = "Тур", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        booking?.tour_price?.let {
                            Text(
                                text = it.toString(),
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                    ) {

                        Text(
                            text = "Топливный сбор", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        booking?.fuel_charge?.let {
                            Text(
                                text = it.toString(),
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        Text(
                            text = "Сервисный сбор", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        booking?.service_charge?.let {
                            Text(
                                text = it.toString(),
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        Text(
                            text = "К оплате", fontSize = 16.sp, color = Color(0xFF828796)
                        )
                        Text(
                            text = sum(
                                booking?.tour_price,
                                booking?.fuel_charge,
                                booking?.service_charge
                            ),
                            fontSize = 16.sp,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(bottom = 6.dp)
                        )
                    }
                }
            }
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 5.dp,
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            navController.navigate("final")
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(343.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(android.graphics.Color.parseColor("#0D72FF"))
                        ),
                        enabled = !passengerList.passengerList.any {
                            it.hasEmptyProperty
                        } && validateEmail(email) && phoneNumber.length == 10

                    ) {
                        Text(
                            text = "К оплате ${
                                sum(
                                    booking?.tour_price,
                                    booking?.fuel_charge,
                                    booking?.service_charge
                                )
                            }"
                        )
                    }
                }
            }
        }
    }
}

private fun sum(a: Int?, b: Int?, c: Int?): String {
    val result = (a?.plus(b ?: 0) ?: 0) + (c ?: 0)
    return result.toString()
}