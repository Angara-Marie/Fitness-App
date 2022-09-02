package dev.angara.fitnessapp.retrofit

import dev.angara.fitnessapp.models.RegisterRequest
import dev.angara.fitnessapp.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}