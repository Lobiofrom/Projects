package com.example.coctails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.coctails.presentation.MyApp
import com.example.coctails.presentation.MyViewModel
import com.example.coctails.presentation.MyViewModelFactory
import com.example.coctails.ui.theme.CoctailsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MyViewModel by viewModels {
            MyViewModelFactory(application)
        }
        setContent {
            CoctailsTheme {

//                Column(modifier = Modifier.fillMaxSize()) {
//                    var isVisible by remember {
//                        mutableStateOf(false)
//                    }
//                    Button(onClick = { isVisible = ! isVisible }) {
//
//                    }
//                    AnimatedVisibility(visible = isVisible,
//                        modifier = Modifier.fillMaxWidth().weight(1f)) {
//                        Box(modifier = Modifier.background(Color.Red))
//                    }
//                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModel)
                }
            }
        }
    }
}