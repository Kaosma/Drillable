package com.creativeleague.drillable

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.practice_drill_item.view.*

class DrillRecyclerAdapter(private val context: Context, private val drills: List<Drill>) : RecyclerView.Adapter<DrillRecyclerAdapter.ViewHolder>(){

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.drill_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = drills.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drill = drills[position]
        holder.textViewName.text = drill.name
        holder.textViewContent.text = drill.content
        holder.textViewRating.text = drill.rating.toString()
        holder.drillPosition = position
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.textName)
        val textViewContent = itemView.findViewById<TextView>(R.id.textContent)
        val textViewRating = itemView.findViewById<TextView>(R.id.textRating)
        val rateButton = itemView.findViewById<Button>(R.id.rateButton)
        val drillImage = itemView.findViewById<ImageView>(R.id.drillImage)
        val addButton = itemView.findViewById<Button>(R.id.addButton)
        val drillRecyclerView = itemView.findViewById<RecyclerView>(R.id.drillRecyclerView)
        val addDrillRecyclerView = itemView.findViewById<RecyclerView>(R.id.addDrillRecyclerView)
        var drillPosition = 0

        init {
            addButton.setOnClickListener {
                //val intent = Intent(context, PracticePlannerActivity::class.java)
                Log.i("HEJSAN", "SVEJSAN")
            }
        }
    }
}