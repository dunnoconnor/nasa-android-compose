package com.example.nasa_apod.ui.screens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.nasa_apod.network.NasaApi
import com.example.nasa_apod.network.NasaPhoto
import java.io.IOException

sealed interface NasaUiState {
    data class Success(val photo: NasaPhoto) : NasaUiState
    object Error : NasaUiState
    object Loading : NasaUiState
}

class NasaViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var nasaUiState: NasaUiState by mutableStateOf(NasaUiState.Loading)
        private set

    private val _photo = MutableLiveData<NasaPhoto>()
    val photo: LiveData<NasaPhoto> = _photo
    /**
     * Call getNasaPhotos() on init so we can display status immediately.
     */
    init {
        getNasaPhoto()
    }

    /**
     * Gets Nasa photo information from the Nasa API Retrofit service and updates the
     */
    private fun getNasaPhoto() {
        viewModelScope.launch {
            nasaUiState = try {
                _photo.value = NasaApi.retrofitService.getPhotos("Fbc6i3XjnJIJcmQSZzwaMfojz9A5DIgQXJBbIIZE")
                NasaUiState.Success(
                    _photo.value!!
                )
            } catch (e: IOException) {
                NasaUiState.Error
            }
        }
    }
}
