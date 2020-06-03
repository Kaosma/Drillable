package adapters

import android.app.AlertDialog
import android.content.*
import android.graphics.Color
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.creativeleague.drillable.*
import activities.ViewDrillActivity
import com.google.android.material.snackbar.Snackbar

class PracticePlannerRecyclerAdapter(private val context: Context, private val chosenDrills: List<Drill>) : RecyclerView.Adapter<PracticePlannerRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.practiceplanner_drill_item,parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = chosenDrills.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drill = chosenDrills[position]
        holder.drillName.text = drill.name
        holder.drillNumber.text = (position+1).toString()
        holder.drillPosition = position
    }

    fun removeDrill(position: Int) {
        DataManager.chosenDrills.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val drillNumber = itemView.findViewById<Button>(R.id.numberOfDrillButton)
        val drillName = itemView.findViewById<Button>(R.id.nameOfDrillButton)
        val deleteDrillButton = itemView.findViewById<ImageButton>(R.id.deleteDrillButton)
        var drillPosition = 0

        init {
            deleteDrillButton.setOnClickListener { view ->
                val drill = chosenDrills[drillPosition].name
                val dialogBuilder = AlertDialog.Builder(context)

                dialogBuilder.setTitle("Remove Drill?")
                    .setMessage("This will remove \"$drill\" from your practice")
                    .setPositiveButton("Remove", DialogInterface.OnClickListener { dialog, id ->
                        removeDrill(drillPosition)
                        val snackbar = Snackbar.make(view, "Drill Removed", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.parseColor("#FC5C14"))
                            .setTextColor(Color.parseColor("#F4F4F4")).show()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
                
                val alert = dialogBuilder.create()
                alert.show()
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
            }
            drillName.setOnClickListener {
                val intent = Intent(context, ViewDrillActivity::class.java)
                intent.putExtra("Index", drillPosition)
                intent.putExtra("Activity", "${context.javaClass.simpleName}")
                context.startActivity(intent)
            }
        }
    }
}