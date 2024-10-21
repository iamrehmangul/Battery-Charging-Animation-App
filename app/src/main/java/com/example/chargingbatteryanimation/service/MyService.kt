package com.example.chargingbatteryanimation.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import com.example.chargingbatteryanimation.data.getAnimationPreference
import com.example.chargingbatteryanimation.receiver.ChargingReceiver
import com.example.chargingbatteryanimation.screen.ChargingActivity

class MyService : Service() {

    private lateinit var chargingReceiver: ChargingReceiver

    override fun onCreate() {
        super.onCreate()

        chargingReceiver = ChargingReceiver()

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }

        registerReceiver(chargingReceiver,filter)

    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val selectedAnimations = getAnimationPreference(this)

        val animationIntent = Intent(this,ChargingActivity::class.java)
        animationIntent.putExtra("SelectedAnimtion",selectedAnimations)
        animationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(animationIntent)


        return START_NOT_STICKY
    }



    override fun onBind(intent : Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(chargingReceiver)
    }
}