package dev.angara.fitnessapp.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.angara.fitnessapp.databinding.ActivitySignUpBinding
import dev.angara.fitnessapp.models.RegisterRequest
import dev.angara.fitnessapp.models.RegisterResponse
import dev.angara.fitnessapp.retrofit.ApiClient
import dev.angara.fitnessapp.retrofit.ApiInterface
import dev.angara.fitnessapp.viewModel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels()
    lateinit var sharedPrefs:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences("FITNESSAPP_PREFS", MODE_PRIVATE)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            validateSignUp()
        }
//        binding.tvLogin.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }
    }


    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { registerResponse ->
            Toast.makeText(baseContext,registerResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
        })
        userViewModel.loginErrorLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
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
            userViewModel.registerUser(registerRequest)
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }



}