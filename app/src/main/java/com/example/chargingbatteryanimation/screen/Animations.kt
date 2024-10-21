package com.example.chargingbatteryanimation.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chargingbatteryanimation.R
import com.example.chargingbatteryanimation.adaptor.AnimationAdaptor
import com.example.chargingbatteryanimation.data.saveAnimationPreference
import com.example.chargingbatteryanimation.databinding.ActivityAnimationsBinding
import com.example.chargingbatteryanimation.model.AnimationModel

class Animations : AppCompatActivity() {

    val binding by lazy {
        ActivityAnimationsBinding.inflate(layoutInflater)
    }
    private lateinit var  adapter : AnimationAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val lottieAnimation = listOf(

            AnimationModel(R.raw.a),
            AnimationModel(R.raw.b),
            AnimationModel(R.raw.c),
            AnimationModel(R.raw.d),
            AnimationModel(R.raw.e),
            AnimationModel(R.raw.f),
            AnimationModel(R.raw.g),
            AnimationModel(R.raw.h),

            )


        adapter = AnimationAdaptor(this,lottieAnimation){AnimationSelected->
            saveAnimationPreference(this,AnimationSelected)
            Toast.makeText(this,"Animation Selected",Toast.LENGTH_SHORT).show()

        }
        binding.animationRv.adapter = adapter
        binding.animationRv.layoutManager=GridLayoutManager(this,2)



    }
}