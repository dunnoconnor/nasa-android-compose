package com.example.nasa_apod.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NasaPhoto(
    val copyright: String = "",
    val date : String,
    val explanation: String,
    @SerialName("hdurl")
    val imageUrl: String,
    val media_type : String,
    @SerialName("service_version")
    val serviceVersion :String,
    val title: String,
    val url: String,
)