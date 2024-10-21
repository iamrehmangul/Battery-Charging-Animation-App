package com.example.chargingbatteryanimation.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chargingbatteryanimation.databinding.AnimationRvSampleBinding
import com.example.chargingbatteryanimation.model.AnimationModel
import com.example.chargingbatteryanimation.model.SlideModel

class AnimationAdaptor(
    var context: Context,
    var animationList: List<AnimationModel>,
    var AnimationSelcted : (Int) -> Unit
) :
    RecyclerView.Adapter<AnimationAdaptor.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = AnimationRvSampleBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animationList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.lottieAnimation.setAnimation(animationList[position].animationId)
        holder.binding.lottieAnimation.playAnimation()

        holder.itemView.setOnClickListener {
            AnimationSelcted(animationList[position].animationId)
        }

    }

    inner class ViewHolder(val binding: AnimationRvSampleBinding):RecyclerView.ViewHolder(binding.root)

}