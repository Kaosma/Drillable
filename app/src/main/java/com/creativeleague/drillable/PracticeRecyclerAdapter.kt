package com.creativeleague.drillable

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PracticeRecyclerAdapter(private val context: Context, private val chosenDrills: List<Drill>) : RecyclerView.Adapter<PracticeRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val drillNumber = itemView.findViewById<Button>(R.id.numberOfDrillButton)
        val drillName = itemView.findViewById<Button>(R.id.nameOfDrillButton)
    }

    private val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.practice_drill_item,parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = chosenDrills.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drill = chosenDrills[position]
        holder.drillName.text = drill.name
        holder.drillNumber.text = position.toString()
    }
}