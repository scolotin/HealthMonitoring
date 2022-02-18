package com.scolotin.healthmonitoring.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scolotin.healthmonitoring.databinding.ItemHealthBinding
import com.scolotin.healthmonitoring.model.HealthData

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var oldDate: String = ""

    private var data: List<HealthData> = listOf()

    fun submit(data: List<HealthData>) {
        this.data = data
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        data[position].apply {
            holder.bind(this, isNewDate(this.date))
            oldDate = this.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            ItemHealthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = data.size

    private fun isNewDate(date: String): Boolean =
        (oldDate != date || oldDate.isEmpty())
}
