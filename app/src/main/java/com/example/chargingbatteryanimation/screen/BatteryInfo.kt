package com.example.chargingbatteryanimation.screen

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chargingbatteryanimation.MainActivity
import com.example.chargingbatteryanimation.R
import com.example.chargingbatteryanimation.databinding.ActivityBatteryInfoBinding

class BatteryInfo : AppCompatActivity() {
    private val binding by lazy {
        ActivityBatteryInfoBinding.inflate(layoutInflater)
    }
    lateinit var batteryInfoReceiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        setSupportActionBar(binding.materialToolbar)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val batteryInfoReceiver = object : BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            override fun onReceive(context: Context?, intent: Intent?) {


                intent?.let {
                    val health = when (it.getIntExtra(BatteryManager.EXTRA_HEALTH, 0)) {

                        BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
                        BatteryManager.BATTERY_HEALTH_OVERHEAT -> "OverHeat"
                        BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
                        BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over Voltage"
                        BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Failure"
                        BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
                        else -> "Unknown"

                    }
                    binding.batteryHealth.text = health

                    //Temperature
                    val temp = it.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10.0
                    binding.batteryTemp.text = " $temp C"

                    // Voltage
                    val voltage = it.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)
                    binding.batteryVoltage.text = "$voltage"

                    //Technology
                    val technology = it.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)
                    binding.batteryTech.text = technology

                    //Plugged
                    val plugged = when (it.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)) {

                        BatteryManager.BATTERY_PLUGGED_AC -> "AC Plugged"
                        BatteryManager.BATTERY_PLUGGED_USB -> "USB Plugged"
                        BatteryManager.BATTERY_PLUGGED_WIRELESS -> "Wireless Plugged"
                        else -> "Not Plugged"

                    }
                    binding.batteryPlugged.text = plugged

                    //Capacity
                    val capacity = batteryGetCapacity(this@BatteryInfo)
                    binding.batteryCapacity.text = "$capacity mAh"


                }

            }

        }


        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryInfoReceiver, intentFilter)


    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryInfoReceiver)
    }

    private fun batteryGetCapacity(context: Context): Double? {
        return try {
            val powerProfileClass = Class.forName("com.android.internal.os.PowerProfile")
            val powerProfile =
                powerProfileClass.getConstructor(Context::class.java).newInstance(context)
            val batteryCapacityMethod = powerProfileClass.getMethod("getBatteryCapacity")
            batteryCapacityMethod.invoke(powerProfile) as? Double
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }


    }
}
