package com.example.nasa_apod.ui.screens
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nasa_apod.R
import com.example.nasa_apod.ui.theme.NasaapodTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import coil.compose.AsyncImage
import com.example.nasa_apod.network.NasaPhoto

@Composable
fun HomeScreen(
    nasaUiState: NasaUiState,
    modifier: Modifier = Modifier
) {
    when (nasaUiState) {
        is NasaUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is NasaUiState.Success -> ResultScreen(
            nasaUiState.photo, modifier = modifier.fillMaxWidth()
        )
        is NasaUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}

/**
 * ResultScreen displaying photo retrieved.
 */
@Composable
fun ResultScreen(photo: NasaPhoto, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = photo.url,
            contentDescription = photo.title
        )
        Text(
            photo.explanation,
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}