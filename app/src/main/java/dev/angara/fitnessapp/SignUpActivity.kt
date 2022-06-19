package dev.angara.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.angara.fitnessapp.databinding.ActivitySignUpBinding

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
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
    fun validateSignUp(){
        var firstName = binding.etFisrtName.text.toString()
        var secondName = binding.etSecondName.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword2.text.toString()
        var confirm = binding.etConfirm.text.toString()

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
    }
}