package com.example.chargingbatteryanimation.screen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chargingbatteryanimation.R
import com.example.chargingbatteryanimation.databinding.ActivityChargingBinding

class ChargingActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityChargingBinding.inflate(layoutInflater)
    }

    private val Finalreceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            if (intent?.action == "com.example.ACTION_FINISH_CHARGING_ACTIVITY") {
                finish()
            }

        }
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


        val filter = IntentFilter("com.example.ACTION_FINISH_CHARGING_ACTIVITY")
        registerReceiver(Finalreceiver, filter)


        val selectedAnimations = intent.getIntExtra("SelectedAnimation", R.raw.d)

        binding.chargingAnimation.setAnimation(selectedAnimations)
        binding.chargingAnimation.playAnimation()
        binding.chargingAnimation.loop(true)


    }

    override fun onDestroy() {
        super.onDestroy()

        // Unregister the receiver to avoid memory leaks
        unregisterReceiver(Finalreceiver)
    }
}