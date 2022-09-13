package dev.angara.fitnessapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.angara.fitnessapp.models.LoginRequest
import dev.angara.fitnessapp.models.LoginResponse
import dev.angara.fitnessapp.models.RegisterRequest
import dev.angara.fitnessapp.models.RegisterResponse
import dev.angara.fitnessapp.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel :ViewModel(){
    val userRepository=UserRepository()
    var loginResponseLiveData=MutableLiveData<LoginResponse>()
    val loginErrorLiveData=MutableLiveData<String?>()
    var registerResponseLiveData=MutableLiveData<RegisterResponse>()
    val registerErrorLiveData=MutableLiveData<String?>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response=userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())

            }else{
                val error=response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }
        }

    }

    fun registerUser(registerRequests: RegisterRequest){
        viewModelScope.launch {
            val response=userRepository.registerUser(registerRequests)
            if (response.isSuccessful){
                registerResponseLiveData.postValue(response.body())

            }else{
                val error=response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }

    }

}