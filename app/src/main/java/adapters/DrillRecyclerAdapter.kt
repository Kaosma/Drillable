package adapters

import activities.MainActivity
import activities.PracticePlannerActivity
import activities.ViewDrillActivity
import android.app.*
import android.content.*
import android.graphics.Color
import android.util.*
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.creativeleague.drillable.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class DrillRecyclerAdapter(private val context: Context, private val drills: List<Drill>) : RecyclerView.Adapter<DrillRecyclerAdapter.ViewHolder>(){

    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    private val layoutInflater = LayoutInflater.from(context)
    private var drillRating : Int = 0
    var myRating : Float = 0F

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
        if(drill.rating[auth.uid] != null) {
            drillRating = drill.rating[auth.uid]!!
        }
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.textName)
        val textViewContent = itemView.findViewById<TextView>(R.id.textContent)
        val textViewRating = itemView.findViewById<TextView>(R.id.textRating)
        val rateButton = itemView.findViewById<Button>(R.id.rateButton)
        val viewButton = itemView.findViewById<Button>(R.id.viewButton)
        val addButton = itemView.findViewById<Button>(R.id.addButton)
        var ratingBar : RatingBar? = null
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
                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setView(R.layout.dialog_rating)
                    .setTitle("Rate this drill?")
                    .setPositiveButton("Save", DialogInterface.OnClickListener { dialog, id ->
                        val id = drills[drillPosition].id
                        db.collection("drills").document(id).collection("ratings").add(id to myRating)
                            .addOnSuccessListener { documentReference ->
                                Log.d("DRILL ADDED TO DATABASE", "DocumentSnapshot added with ID: " + documentReference.id)
                            }
                            .addOnFailureListener { e ->
                                Log.w("FAILED TO ADD DRILL", "Error adding document", e)
                            }
                        Toast.makeText(context, "You rated ${drills[drillPosition].name} with ${myRating} stars", Toast.LENGTH_SHORT)
                            .show()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

                val alert = dialogBuilder.create()
                alert.show()
                val ratingBar = alert.findViewById<RatingBar>(R.id.ratingBar)
                ratingBar?.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                    myRating = ratingBar?.rating!!
                }
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
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
