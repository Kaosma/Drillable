package adapters

import activities.MainActivity
import activities.PracticePlannerActivity
import activities.ViewDrillActivity
import android.content.*
import android.view.*
import android.widget.*
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

        drill.rating?.forEach { (key, value) ->
            totalRating += value
        }
        holder.textViewRating.text = (totalRating/ drill.rating?.size!!.toDouble()).toString()
        holder.drillPosition = position
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.textName)
        val textViewContent = itemView.findViewById<TextView>(R.id.textContent)
        val textViewRating = itemView.findViewById<TextView>(R.id.textRating)
        val rateButton = itemView.findViewById<Button>(R.id.rateButton)
        val viewButton = itemView.findViewById<Button>(R.id.viewButton)
        val addButton = itemView.findViewById<Button>(R.id.addButton)
        var drillPosition = 0

        init {
            if (context is MainActivity) {
                addButton.visibility = View.INVISIBLE
            }
            addButton.setOnClickListener {
                DataManager.chosenDrills.add(drills[drillPosition])
                val intent = Intent(context, PracticePlannerActivity::class.java)
                context.startActivity(intent)
            }
            rateButton.setOnClickListener {
                //DataManager.drills
            }
            viewButton.setOnClickListener{
                val intent = Intent(context, ViewDrillActivity::class.java)
                intent.putExtra("Index", drillPosition)
                intent.putExtra("Activity", "${context.javaClass.simpleName}")
                context.startActivity(intent)
            }
        }
    }
}
