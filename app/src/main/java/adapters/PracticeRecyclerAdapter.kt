package adapters

import activities.ViewDrillActivity
import android.content.*
import android.graphics.Color
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.creativeleague.drillable.*
import com.google.android.material.snackbar.Snackbar

class PracticeRecyclerAdapter(private val context: Context, private val chosenDrills: List<Drill>) : RecyclerView.Adapter<PracticeRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.practice_drill_item,parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = chosenDrills.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drill = chosenDrills[position]
        holder.drillName.text = drill.name
        holder.drillNumber.text = (position+1).toString()
        holder.drillMinutes.text = drill.length.toString()
        holder.drillPosition = position
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val drillNumber = itemView.findViewById<Button>(R.id.numberOfDrillButton)
        val drillName = itemView.findViewById<Button>(R.id.nameOfDrillButton)
        val drillMinutes = itemView.findViewById<Button>(R.id.timeButton)
        var drillPosition = 0

        init {
            drillName.setOnClickListener {
                val intent = Intent(context, ViewDrillActivity::class.java)
                intent.putExtra("Index", drillPosition)
                intent.putExtra("Activity", "${context.javaClass.simpleName}")
                context.startActivity(intent)
            }
        }
    }
}