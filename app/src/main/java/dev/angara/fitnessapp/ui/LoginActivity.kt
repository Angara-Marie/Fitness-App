package dev.angara.fitnessapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.angara.fitnessapp.databinding.ActivityLogin2Binding

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLogin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            validateLogin()
        }
        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
    fun validateLogin(){
        var error = false
        var email = binding.etUserName.text.toString()
        var password = binding.etPassword.text.toString()

        if (email.isBlank()){
            binding.tilUserName.error = "Username required"
            error= true
        }
        if (password.isBlank()){
            binding.tilPassword.error ="Password required"
            error = true
        }
        if (!error){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}