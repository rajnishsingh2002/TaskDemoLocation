package com.simple.taskdemoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StatusAdapter(private val list: List<StatusModel>) :
    RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

    inner class StatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val location = itemView.findViewById<TextView>(R.id.tvLocation)
        val status = itemView.findViewById<TextView>(R.id.tvStatus)
        val date = itemView.findViewById<TextView>(R.id.tvDate)
        val time = itemView.findViewById<TextView>(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_status, parent, false)
        return StatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val item = list[position]
        holder.location.text = item.location
        holder.status.text = item.workStatus
        holder.date.text = item.currentDate
        holder.time.text = item.currentTime
    }

    override fun getItemCount(): Int = list.size
}
