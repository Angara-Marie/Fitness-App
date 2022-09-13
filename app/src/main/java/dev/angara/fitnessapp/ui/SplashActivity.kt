package dev.angara.fitnessapp.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.angara.fitnessapp.R

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPrefs:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences("FITNESSAPP_PREFS", MODE_PRIVATE)
        val accessToken = sharedPrefs.getString("ACCESS_TOKEN", "")
        if (accessToken!!.isNotBlank()){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        finish()
    }

}