package com.example.chargingbatteryanimation.data

import android.content.Context
import com.example.chargingbatteryanimation.R


fun saveAnimationPreference(context: Context,animationId : Int){

    val sharedPreferences = context.getSharedPreferences("ChargingAnimation",Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putInt(" SelectedAnimation",animationId)
    editor.apply()

}


fun getAnimationPreference(context: Context) : Int{
    val sharedPreferences= context.getSharedPreferences("animation",Context.MODE_PRIVATE)
    return sharedPreferences.getInt("SelectedAnimation", R.raw.a)
}