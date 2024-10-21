package com.example.chargingbatteryanimation.screen

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.chargingbatteryanimation.MainActivity
import com.example.chargingbatteryanimation.databinding.ActivityDeviceInfoBinding
import java.io.File

class DeviceInfo : AppCompatActivity() {

    private val binding by lazy {
        ActivityDeviceInfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        updateDeviceInfo()


    }

    @SuppressLint("SetTextI18n")
    private fun updateDeviceInfo() {

        //Device Name
        binding.deviceName.text = android.os.Build.MODEL

        //Device ID
        val deviceId = android.os.Build.ID
        binding.deviceId.text = deviceId

        //SDK Version
        val sdkVersion = android.os.Build.VERSION.SDK_INT
        binding.sdkVersion.text = sdkVersion.toString()

        //Android Version
        val androidVersion = android.os.Build.VERSION.RELEASE
        binding.androidVersion.text = androidVersion

        //Ram
        val totalRam = getTotalRam()
        binding.ram.text = "$totalRam GB"

        //Storage
        val totalStorage = getTotalStorage()
        binding.storage.text = "$totalStorage GB"


    }


    private fun getTotalRam(): Long {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        return memoryInfo.totalMem / (1024 * 1024 * 1024)
    }

    private fun getTotalStorage(): Long {
        val stat = File(Environment.getDataDirectory().path)
        val totalBytes = stat.totalSpace
        return totalBytes / (1024 * 1024 * 1024)
    }
}