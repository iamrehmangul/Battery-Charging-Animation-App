package com.example.chargingbatteryanimation

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.example.chargingbatteryanimation.databinding.ActivityMainBinding
import com.example.chargingbatteryanimation.model.SlideModel
import com.example.chargingbatteryanimation.receiver.ChargingReceiver
import com.example.chargingbatteryanimation.screen.Animations
import com.example.chargingbatteryanimation.screen.BatteryInfo
import com.example.chargingbatteryanimation.screen.ChargingActivity
import com.example.chargingbatteryanimation.screen.DeviceInfo
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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


        //Image Slider
        val imagelist = ArrayList<com.denzcoskun.imageslider.models.SlideModel>()

        imagelist.add(com.denzcoskun.imageslider.models.SlideModel(R.drawable.a))
        imagelist.add(com.denzcoskun.imageslider.models.SlideModel(R.drawable.b))
        imagelist.add(com.denzcoskun.imageslider.models.SlideModel(R.drawable.c))

       binding.imageSlider.setImageList(
           imageList = imagelist,
           ScaleTypes.FIT
       )

        binding.animations.setOnClickListener {
            startActivity(Intent(this,Animations::class.java))
        }
        binding.batteryInfo.setOnClickListener {
            startActivity(Intent(this,BatteryInfo::class.java))
        }
        binding.deviceInfo.setOnClickListener {
            startActivity(Intent(this,DeviceInfo::class.java))
        }

         
    }
}