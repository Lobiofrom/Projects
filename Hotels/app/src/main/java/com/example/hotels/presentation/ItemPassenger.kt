package com.example.hotels.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.Passenger
import com.example.domain.models.TextField
import com.example.hotels.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemPassenger(
    passenger: Passenger,
    onTextChange: (TextField, Passenger) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isExpanded by remember {
        mutableStateOf(true)
    }
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 5.dp,
        modifier = Modifier.padding(top = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Турист ",
                    style = TextStyle(
                        fontSize = 22.sp,
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = passenger.touristCount.toString(),
                    style = TextStyle(
                        fontSize = 22.sp,
                    ),
                    modifier = Modifier
                        .padding(start = 100.dp)
                        .align(Alignment.CenterStart)
                )
                Image(
                    painter = painterResource(id = if (isExpanded) R.drawable.expanded else R.drawable.not_expanded),
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
                        ) { isExpanded = !isExpanded },
                )

            }
            Column(
                modifier = Modifier
                    .height(if (isExpanded) 360.dp else 0.dp)
                    .padding(top = 6.dp)
                    .alpha(if (isExpanded) 1f else 0f)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                var name by rememberSaveable { mutableStateOf("") }
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                        TextField.NAME.property = it
                        onTextChange(TextField.NAME, passenger)
                    },
                    label = {
                        Text(
                            text = "Имя"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = if (name.isNotEmpty()) Color(0xFFF6F6F9)
                        else Color(0xFFEB5757)
                    ),
                    modifier = Modifier
                        .width(343.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 3.dp)
                        .width(343.dp)
                )

                var surname by rememberSaveable { mutableStateOf("") }

                TextField(
                    value = surname,
                    onValueChange = {
                        surname = it
                        TextField.SURNAME.property = it
                        onTextChange(TextField.SURNAME, passenger)
                    },
                    label = {
                        Text(
                            text = "Фамилия"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = if (surname.isNotEmpty()) Color(0xFFF6F6F9)
                        else Color(0xFFEB5757)
                    ),
                    modifier = Modifier
                        .width(343.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 3.dp)
                        .width(343.dp)
                )

                var birthDate by rememberSaveable { mutableStateOf("") }

                TextField(
                    value = birthDate,
                    onValueChange = {
                        birthDate = it
                        TextField.BIRTHDAY.property = it
                        onTextChange(TextField.BIRTHDAY, passenger)
                    },
                    label = {
                        Text(
                            text = "Дата рождения"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = if (birthDate.isNotEmpty()) Color(0xFFF6F6F9)
                        else Color(0xFFEB5757)
                    ),
                    modifier = Modifier
                        .width(343.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 3.dp)
                        .width(343.dp)
                )

                var nationality by rememberSaveable { mutableStateOf("") }

                TextField(
                    value = nationality,
                    onValueChange = {
                        nationality = it
                        TextField.NATIONALITY.property = it
                        onTextChange(TextField.NATIONALITY, passenger)
                    },
                    label = {
                        Text(
                            text = "Граждансвто"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = if (nationality.isNotEmpty()) Color(0xFFF6F6F9)
                        else Color(0xFFEB5757)
                    ),
                    modifier = Modifier
                        .width(343.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 3.dp)
                        .width(343.dp)
                )

                var passportNumber by rememberSaveable { mutableStateOf("") }

                TextField(
                    value = passportNumber,
                    onValueChange = {
                        passportNumber = it
                        TextField.PASSPORT_NUMBER.property = it
                        onTextChange(TextField.PASSPORT_NUMBER, passenger)
                    },
                    label = {
                        Text(
                            text = "Номер загранпаспорта"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = if (passportNumber.isNotEmpty()) Color(0xFFF6F6F9)
                        else Color(0xFFEB5757)
                    ),
                    modifier = Modifier
                        .width(343.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 3.dp)
                        .width(343.dp)
                )

                var passportExpiery by rememberSaveable { mutableStateOf("") }

                TextField(
                    value = passportExpiery,
                    onValueChange = {
                        passportExpiery = it
                        TextField.PASSPORT_DATE.property = it
                        onTextChange(TextField.PASSPORT_DATE, passenger)
                    },
                    label = {
                        Text(
                            text = "Срок действия загранпаспорта"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = if (passportExpiery.isNotEmpty()) Color(0xFFF6F6F9)
                        else Color(0xFFEB5757)
                    ),
                    modifier = Modifier
                        .width(343.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 3.dp)
                        .width(343.dp)
                )
            }
        }
    }
}