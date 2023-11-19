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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.rememberAsyncImagePainter
import com.example.domain.models.Room
import com.example.hotels.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemRoom(
    room: Room,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 6.dp)
    ) {
        val pagerState = rememberPagerState(pageCount = {
            room.image_urls.size
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
                    .background(color = Color.Black, shape = RoundedCornerShape(20.dp))

            ) { page ->
                val painter = rememberAsyncImagePainter(model = room.image_urls[page])
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(250.dp)
                )
            }
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp), horizontalArrangement = Arrangement.Center
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
        Text(
            text = room.name, fontSize = 22.sp, modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 6.dp, start = 23.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 30.dp, top = 6.dp, end = 16.dp)
        ) {
            items(room.peculiarities) {
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
        Surface(
            modifier = Modifier
                .width(200.dp)
                .height(29.dp)
                .padding(top = 6.dp, start = 23.dp),
            color = Color(0x1A0D72FF),
            shape = RoundedCornerShape(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Подробнее о номере",
                    fontSize = 16.sp,
                    color = Color(0xFF0D72FF),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Image(
                    painter = painterResource(id = R.drawable.img_1),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "${room.price} ₽",
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(start = 23.dp)
                    .alignByBaseline()
            )
            Text(
                text = room.price_per,
                fontSize = 16.sp,
                color = Color(0xFF828796),
                modifier = Modifier
                    .padding(start = 16.dp)
                    .alignByBaseline()
            )
        }
        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(343.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(android.graphics.Color.parseColor("#0D72FF"))
            )

        ) {
            Text(text = "Выбрать номер")
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, top = 6.dp, end = 6.dp, bottom = 6.dp)
        )
    }
}