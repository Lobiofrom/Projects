package com.example.leads.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.data.bottomNaviItem.BottomNaviItem

@Composable
fun BottomNaviBar(
    items: List<BottomNaviItem>,
    navController: NavController,
    onItemClick: (BottomNaviItem) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BottomNavigation(
            modifier = Modifier
                .align(BottomCenter)
                .fillMaxWidth()
                .height(92.dp)
                .background(color = Color.Black, shape = RoundedCornerShape(size = 20.dp))
                .padding(16.dp)
        ) {
            items.forEach {
                val selected = it.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    modifier = Modifier
                        .background(Color.Black),
                    selected = selected,
                    onClick = { onItemClick(it) },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.White,
                    icon = {
                        Column(horizontalAlignment = CenterHorizontally) {
                            if (selected) {
                                Box(
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(20.dp)
                                        .background(
                                            Color(0xFFE8A889),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                ) {
                                    Icon(
                                        painter = painterResource(id = it.icon),
                                        contentDescription = null,
                                        modifier = Modifier.align(Center)
                                    )
                                }
                            } else {
                                Icon(
                                    painter = painterResource(id = it.icon),
                                    contentDescription = null
                                )
                            }
                            Text(
                                text = it.name, style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFFFFFFF),
                                    textAlign = TextAlign.Center,
                                )
                            )

                        }
                    }
                )
            }
        }
    }
}