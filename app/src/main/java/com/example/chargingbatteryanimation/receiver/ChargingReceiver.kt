package com.example.chargingbatteryanimation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.ContentInfoCompat.Flags
import com.example.chargingbatteryanimation.screen.ChargingActivity
import com.example.chargingbatteryanimation.service.MyService

class ChargingReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onReceive(context: Context?, intent: Intent?) {



        when(intent?.action){

            Intent.ACTION_POWER_CONNECTED->{
                val chargingIntent = Intent(context,ChargingActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context?.startActivity(chargingIntent)
            }

            Intent.ACTION_POWER_DISCONNECTED->{
                val chargingIntent = Intent("com.example.ACTION_FINISH_CHARGING_ACTIVITY").apply {
                    flags=Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context?.startActivity(chargingIntent)
            }


        }















//
//        val status = intent?.getIntExtra(BatteryManager.EXTRA_CHARGING_STATUS, -1)
//        val isCharging =
//            status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
//
//        if (isCharging) {
//
//            val serviceIntent = Intent(context, MyService::class.java)
//            context?.startActivity(serviceIntent)
//        }
//
//        if(isCharging){
//
//            val chargingIntent = Intent(context,ChargingActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            }
//        }


    }
}