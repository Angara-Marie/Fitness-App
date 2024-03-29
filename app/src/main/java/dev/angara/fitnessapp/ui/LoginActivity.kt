package dev.angara.fitnessapp.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.angara.fitnessapp.databinding.ActivityLogin2Binding
import dev.angara.fitnessapp.models.LoginRequest
import dev.angara.fitnessapp.models.LoginResponse
import dev.angara.fitnessapp.retrofit.ApiClient
import dev.angara.fitnessapp.retrofit.ApiInterface
import dev.angara.fitnessapp.viewModel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogin2Binding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("FITNESSAPP_PREFS", MODE_PRIVATE)


        binding.btnLogin.setOnClickListener {
            validateLogin()

        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }


    override fun onResume(){
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse ->
            saveLoginDetails(loginResponse!!)
            Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        })
        userViewModel.loginErrorLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }

    fun validateLogin() {
        var error = false
        var email = binding.etUserName.text.toString()
        var password = binding.etPassword.text.toString()

        if (email.isBlank()) {
            binding.tilUserName.error = "Username required"
            error = true
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password required"
            error = true
        }
        if (!error) {
            var loginRequest = LoginRequest(email, password)
            binding.pbLogin.visibility = View.VISIBLE
            userViewModel.loginUser(loginRequest)
        }
    }


    fun saveLoginDetails(loginResponse: LoginResponse) {
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN", loginResponse.accessToken)
        editor.putString("USER_ID", loginResponse.userId)
        editor.putString("PROFILE_ID", loginResponse.profileId)
        editor.apply()
    }
}
