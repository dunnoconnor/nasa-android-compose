package com.example.nasa_apod.network
import retrofit2.Retrofit
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.Query


private const val BASE_URL =
    "https://api.nasa.gov"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface NasaAPIService {
    @GET("planetary/apod")
    suspend fun getPhotos(@Query("api_key")api_key:String): NasaPhoto
}

object NasaApi {
    val retrofitService : NasaAPIService by lazy {
        retrofit.create(NasaAPIService::class.java)
    }
}