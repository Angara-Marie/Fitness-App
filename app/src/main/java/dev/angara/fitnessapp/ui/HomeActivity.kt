package dev.angara.fitnessapp.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.angara.fitnessapp.R
import dev.angara.fitnessapp.databinding.ActivityHomeBinding
import dev.angara.fitnessapp.models.LoginResponse

class HomeActivity : AppCompatActivity() {
    lateinit var binding:ActivityHomeBinding
    lateinit var sharedPrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
        setUpBottomNav()
        binding.imgLogout.setOnClickListener {
            val editor = sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN", "")
            editor.putString("USER_ID", "")
            editor.putString("PROFILE_ID", "")
            editor.apply()
            startActivity(Intent(this,LoginResponse::class.java))
        }
    }
    fun castViews(){
        binding.fcvHome
        binding.bnvHome
    }
    fun setUpBottomNav(){
        binding.bnvHome.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.plan -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, ProfileFragment()).commit()
                    true

                }
                R.id.track ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        ProfileFragment()
                    ).commit()
                    true
                }
                else -> false
            }
        }
    }
}