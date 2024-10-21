package com.example.chargingbatteryanimation.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chargingbatteryanimation.MainActivity
import com.example.chargingbatteryanimation.R
import com.example.chargingbatteryanimation.databinding.ActivityOnboardingBinding

class Onboarding : AppCompatActivity() {

    val binding by lazy {
        ActivityOnboardingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val sharedPreferences = getSharedPreferences("Onboarding", MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isFirstTime", false).apply()


        binding.getStartBtn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }






    }
}