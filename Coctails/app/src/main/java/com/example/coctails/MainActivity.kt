package com.example.coctails

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.coctails.presentation.MyApp
import com.example.coctails.presentation.MyViewModel
import com.example.coctails.presentation.MyViewModelFactory
import com.example.coctails.ui.theme.CoctailsTheme

class MainActivity : ComponentActivity() {

    private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //checkPermissions()

        val viewModel: MyViewModel by viewModels {
            MyViewModelFactory(application)
        }
        setContent {
            CoctailsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModel)
                }
            }
        }
    }

    private fun checkPermissions() {
        val isAllGranted =
            ContextCompat.checkSelfPermission(
                this,
                REQUEST_PERMISSIONS
            ) == PackageManager.PERMISSION_GRANTED

        if (!isAllGranted) launcher.launch(REQUEST_PERMISSIONS)
    }

    companion object {
        private const val REQUEST_PERMISSIONS = Manifest.permission.READ_EXTERNAL_STORAGE
        //: Array<String> = buildList {
        //add(Manifest.permission.READ_EXTERNAL_STORAGE)
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) add(Manifest.permission.READ_MEDIA_IMAGES)
        //}.toTypedArray()
    }
}