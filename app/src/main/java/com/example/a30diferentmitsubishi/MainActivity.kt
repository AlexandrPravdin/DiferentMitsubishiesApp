package com.example.a30diferentmitsubishi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30diferentmitsubishi.Model.cars
import com.example.compose.DifferentMitsubishiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DifferentMitsubishiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CarApp()
                }
            }
        }
    }
}

@Composable
fun CarApp(modifier: Modifier = Modifier){
    CarScreen(cars = cars)
}

@Preview
@Composable
fun CarAppPreview(modifier: Modifier = Modifier){
    DifferentMitsubishiTheme {
        CarScreen(cars = cars)
    }
}