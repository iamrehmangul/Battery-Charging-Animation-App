package com.example.chargingbatteryanimation.screen

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chargingbatteryanimation.MainActivity
import com.example.chargingbatteryanimation.R
import com.example.chargingbatteryanimation.databinding.ActivitySplashBinding
import com.example.chargingbatteryanimation.service.MyService
import java.util.logging.Handler

class Splash : AppCompatActivity() {

    val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        
        android.os.Handler(Looper.getMainLooper()).postDelayed(
            {

                val sharedPreferences = getSharedPreferences("Onboarding", MODE_PRIVATE)
                val isFirstTime = sharedPreferences.getBoolean("isFirstTime", true)
                if (isFirstTime) {
                    val intent = Intent(this, Onboarding::class.java)
                    startActivity(intent)
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                finish()
            }, 3000
        )

        val startServiceIntent = Intent(this, MyService::class.java)
        startService(startServiceIntent)

    }
}