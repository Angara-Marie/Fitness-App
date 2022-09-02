package dev.angara.fitnessapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.angara.fitnessapp.databinding.ActivitySignUpBinding
import dev.angara.fitnessapp.models.RegisterRequest
import dev.angara.fitnessapp.models.RegisterResponse
import dev.angara.fitnessapp.retrofit.ApiClient
import dev.angara.fitnessapp.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            validateSignUp()
        }
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
    fun validateSignUp(){
        var firstName = binding.etFisrtName.text.toString()
        var secondName = binding.etSecondName.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword2.text.toString()
        var confirm = binding.etConfirm.text.toString()
        var phoneNumber = binding.etPhoneNumber.text.toString()

        var error = false

        if (firstName.isBlank()){
            binding.tilFirstName.error= "First name required"
        }
        if (secondName.isBlank()){
            binding.tilSecondName.error= "Second name required"
        }
        if (email.isBlank()){
            binding.tilEmail.error= "Email required"
        }
        if (password.isBlank()){
            binding.tilPassword2.error = "Password required"
        }
        if (confirm.isBlank()){
            binding.tilConfirm.error= "Password mismatch"
        }
        if (password != confirm){
            binding.tilPassword2.error = "Password mismatch"
        }
        if (phoneNumber.isBlank()){
            binding.tilPhoneNumber.error = "Phone number required"
        }

        if (!error){
            val registerRequest= RegisterRequest(firstName,secondName,email,phoneNumber,password)
            makeRegistrationRequest(registerRequest)
        }
    }

    fun makeRegistrationRequest(registerRequest: RegisterRequest){
        var apiClient= ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.registerUser(registerRequest)
        request.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    var message = response.body()?.message
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                }else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}