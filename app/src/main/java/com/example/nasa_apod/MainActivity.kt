package com.example.nasa_apod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nasa_apod.ui.theme.NasaapodTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.foundation.layout.Arrangement
import com.example.nasa_apod.ui.NasaPhotosApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NasaapodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NasaPhotosApp()
                }
            }
        }
    }
}

@Composable
fun NasaImg(){
    Column() {
        Image(
            painter = painterResource(R.drawable.static_nasa),
            contentDescription = "nasa astronomy photo of the day",
            modifier = Modifier
                .height(220.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun SizeSlider(){
    Card() {
        var sliderPosition by remember { mutableStateOf(0f) }
        Column {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImgDate(){
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = null)
        DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))

        Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
    }
}
