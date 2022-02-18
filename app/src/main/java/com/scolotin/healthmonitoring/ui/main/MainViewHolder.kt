package com.scolotin.healthmonitoring.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.scolotin.healthmonitoring.databinding.ItemHealthBinding
import com.scolotin.healthmonitoring.model.HealthData

class MainViewHolder(private val vb: ItemHealthBinding) : RecyclerView.ViewHolder(vb.root) {

    fun bind(data: HealthData, isNewDate: Boolean) {
        with(vb) {
            healthDate.visibility = if (isNewDate) View.VISIBLE else View.GONE
            healthDate.text = data.date
            healthTime.text = data.time
            healthSystolic.text = data.systolic
            healthDiastolic.text = data.diastolic
            healthPulse.text = data.pulse
        }
    }

}
