package adapters

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
import com.creativeleague.drillable.*

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
        var totalRating : Double = 0.0
        var counter = 0
        for (rating in drill.rating) {
            //totalRating += rating[counter]!!
            counter += 1
        }
        holder.textViewRating.text = (totalRating/drill.rating.size.toDouble()).toString()
        holder.drillPosition = position
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.textName)
        val textViewContent = itemView.findViewById<TextView>(R.id.textContent)
        val textViewRating = itemView.findViewById<TextView>(R.id.textRating)
        val rateButton = itemView.findViewById<Button>(R.id.rateButton)
        val viewButton = itemView.findViewById<Button>(R.id.viewButton)
        val addButton = itemView.findViewById<Button>(R.id.addButton)
        val drillImage = itemView.findViewById<ImageView>(R.id.drillImage)
        val drillRecyclerView = itemView.findViewById<RecyclerView>(R.id.drillRecyclerView)
        val addDrillRecyclerView = itemView.findViewById<RecyclerView>(R.id.addDrillRecyclerView)
        var drillPosition = 0

        init {
            addButton.setOnClickListener {
                DataManager.chosenDrills.add(drills[drillPosition])
                val intent = Intent(context, PracticePlannerActivity::class.java)
                context.startActivity(intent)
            }
            rateButton.setOnClickListener {
                DataManager.drills
            }
        }
    }
}