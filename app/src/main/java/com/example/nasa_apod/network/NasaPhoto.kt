package com.example.nasa_apod.network
import kotlinx.serialization.Serializable

@Serializable
data class NasaPhoto(
    val copyright: String,
    val date : String,
    val explanation: String,
    val hdurl: String,
    val media_type : String,
    val service_version :String,
    val title: String,
    val url: String,
)