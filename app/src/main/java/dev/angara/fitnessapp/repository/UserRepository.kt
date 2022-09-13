package dev.angara.fitnessapp.repository

import dev.angara.fitnessapp.models.LoginRequest
import dev.angara.fitnessapp.models.RegisterRequest
import dev.angara.fitnessapp.retrofit.ApiClient
import dev.angara.fitnessapp.retrofit.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: LoginRequest)= withContext(Dispatchers.IO){
        val response = apiClient.login(loginRequest)
        return@withContext response
    }
    suspend fun registerUser(registerRequest: RegisterRequest)=
        withContext(Dispatchers.IO){
        val response= apiClient.registerUser(registerRequest)
            return@withContext response
    }
}