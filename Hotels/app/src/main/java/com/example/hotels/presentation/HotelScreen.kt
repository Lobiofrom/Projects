package com.example.hotels.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.domain.models.Hotel
import com.example.domain.models.States
import com.example.hotels.R
import com.example.hotels.viewModels.HotelViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HotelScreen(
    viewModel: HotelViewModel,
    navController: NavController
) {
    var hotel by remember {
        mutableStateOf<Hotel?>(null)
    }

    var uiState by remember {
        mutableStateOf<States>(States.Loading)
    }

    LaunchedEffect(Unit) {
        viewModel.hotel.collect {
            hotel = it
        }
    }
    LaunchedEffect(Unit) {
        viewModel.state.collect { state ->
            uiState = state
        }
    }

    when (uiState) {
        States.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        States.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp),
                    shadowElevation = 5.dp
                ) {
                    Column {
                        Text(
                            text = "Отель", modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        val pagerState = rememberPagerState(pageCount = {
                            hotel?.image_urls?.size ?: 0
                        })
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .width(343.dp)
                                    .height(257.dp)
                                    .shadow(elevation = 15.dp, shape = RoundedCornerShape(20.dp))
                                    .background(
                                        color = Color.Black,
                                        shape = RoundedCornerShape(20.dp)
                                    )

                            ) { page ->
                                AsyncImage(
                                    modifier = Modifier.height(250.dp),
                                    model = hotel?.image_urls?.get(page),
                                    contentScale = ContentScale.Crop,
                                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                                    error = painterResource(R.drawable.ic_launcher_foreground),
                                    contentDescription = null
                                )
                            }
                            Row(
                                Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                repeat(pagerState.pageCount) { iteration ->
                                    val color =
                                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                                    Box(
                                        modifier = Modifier
                                            .padding(2.dp)
                                            .clip(CircleShape)
                                            .background(color)
                                            .size(16.dp)
                                    )
                                }
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .width(149.dp)
                                .height(29.dp)
                                .padding(top = 6.dp, start = 23.dp),
                            color = Color(0x33FFC700),
                            shape = RoundedCornerShape(5.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = null,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                                Text(
                                    text = hotel?.rating.toString(),
                                    fontSize = 15.sp,
                                    color = Color(0xFFFFA800),
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                                hotel?.rating_name?.let {
                                    Text(
                                        text = it,
                                        fontSize = 15.sp,
                                        color = Color(0xFFFFA800),
                                        modifier = Modifier.align(Alignment.CenterVertically)
                                    )
                                }
                            }

                        }
                        hotel?.name?.let {
                            Text(
                                text = it, fontSize = 22.sp,
                                modifier = Modifier.padding(top = 6.dp, start = 23.dp),
                            )
                        }
                        hotel?.let {
                            Text(
                                text = it.adress, fontSize = 14.sp, color = Color(0xFF0D72FF),
                                modifier = Modifier.padding(top = 6.dp, start = 23.dp),
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "от ${hotel?.minimal_price} ₽",
                                fontSize = 30.sp,
                                modifier = Modifier
                                    .padding(start = 23.dp)
                                    .alignByBaseline()
                            )
                            Text(
                                text = "за тур с перелётом",
                                fontSize = 16.sp,
                                color = Color(0xFF828796),
                                modifier = Modifier
                                    .padding(start = 16.dp, bottom = 16.dp)
                                    .alignByBaseline()
                            )
                        }
                    }
                }

                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(top = 6.dp),
                    shadowElevation = 5.dp
                ) {
                    Column {
                        Text(
                            text = "Об отеле",
                            fontSize = 22.sp,
                            modifier = Modifier.padding(start = 23.dp, top = 16.dp)
                        )
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            state = rememberLazyGridState(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(start = 30.dp, top = 6.dp, end = 16.dp)
                        ) {
                            hotel?.about_the_hotel?.let {
                                items(it.peculiarities) {
                                    Surface(
                                        color = Color(android.graphics.Color.parseColor("#FBFBFC")),
                                        shape = RoundedCornerShape(5.dp),
                                        modifier = Modifier
                                            .padding(3.dp)
                                    ) {
                                        Text(
                                            text = it,
                                            fontSize = 16.sp,
                                            color = Color(android.graphics.Color.parseColor("#828796")),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                        hotel?.about_the_hotel?.let {
                            Text(
                                text = it.description,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(start = 23.dp, top = 16.dp, end = 16.dp)
                            )
                        }
                        Surface(
                            color = Color(android.graphics.Color.parseColor("#FBFBFC")),
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .width(343.dp)
                                .height(184.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.smile),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(24.dp)
                                            .width(24.dp)
                                            .align(Alignment.CenterStart)
                                    )
                                    Column(
                                        modifier = Modifier
                                            .align(Alignment.CenterStart)
                                    ) {
                                        Text(
                                            text = "Удобства",
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(start = 36.dp)
                                        )
                                        Text(
                                            text = "Самое необходимое",
                                            fontSize = 14.sp,
                                            color = Color(0xFF828796),
                                            modifier = Modifier.padding(start = 36.dp)
                                        )
                                    }
                                    Image(
                                        painter = painterResource(id = R.drawable.arrow),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(30.dp)
                                            .width(30.dp)
                                            .align(Alignment.CenterEnd)
                                            .padding(start = 15.dp)
                                    )
                                }
                                Divider(
                                    modifier = Modifier
                                        .width(275.dp)
                                        .padding(start = 36.dp, top = 8.dp, bottom = 6.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.img),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(24.dp)
                                            .width(24.dp)
                                            .align(Alignment.CenterStart)
                                    )
                                    Column(
                                        modifier = Modifier
                                            .align(Alignment.CenterStart)
                                    ) {
                                        Text(
                                            text = "Что включено",
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(start = 36.dp)
                                        )
                                        Text(
                                            text = "Самое необходимое",
                                            fontSize = 14.sp,
                                            color = Color(0xFF828796),
                                            modifier = Modifier.padding(start = 36.dp)
                                        )
                                    }
                                    Image(
                                        painter = painterResource(id = R.drawable.arrow),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(30.dp)
                                            .width(30.dp)
                                            .align(Alignment.CenterEnd)
                                            .padding(start = 15.dp)
                                    )
                                }
                                Divider(
                                    modifier = Modifier
                                        .width(275.dp)
                                        .padding(start = 36.dp, top = 8.dp, bottom = 6.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.img_2),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(24.dp)
                                            .width(24.dp)
                                            .align(Alignment.CenterStart)
                                    )
                                    Column(
                                        modifier = Modifier
                                            .align(Alignment.CenterStart)
                                    ) {
                                        Text(
                                            text = "Что не включено",
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(start = 36.dp)
                                        )
                                        Text(
                                            text = "Самое необходимое",
                                            fontSize = 14.sp,
                                            color = Color(0xFF828796),
                                            modifier = Modifier.padding(start = 36.dp)
                                        )
                                    }
                                    Image(
                                        painter = painterResource(id = R.drawable.arrow),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(30.dp)
                                            .width(30.dp)
                                            .align(Alignment.CenterEnd)
                                            .padding(start = 15.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(),
                    shadowElevation = 5.dp
                ) {
                    Column {
                        Button(
                            onClick = {
                                navController.navigate("rooms/${hotel?.name}")
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 6.dp, bottom = 6.dp)
                                .width(343.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(android.graphics.Color.parseColor("#0D72FF"))
                            )

                        ) {
                            Text(text = "К выбору номера")
                        }
                    }
                }
            }
        }
    }
}