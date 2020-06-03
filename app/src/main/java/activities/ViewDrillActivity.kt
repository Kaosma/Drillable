package activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import com.creativeleague.drillable.*
import com.google.firebase.firestore.FirebaseFirestore
import fragments.DrillBankFragment

class ViewDrillActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    val drills = mutableListOf<Drill>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_drill)
        drillsFromDatabase()
        val drillTitle = findViewById<TextView>(R.id.drillTitle)
        val drillContent = findViewById<TextView>(R.id.drillContent)
        val drillRating = findViewById<TextView>(R.id.drillRating)
        val rateButton = findViewById<Button>(R.id.drillRateButton)
        val viewButton = findViewById<Button>(R.id.drillViewButton)
        val addButton = findViewById<Button>(R.id.drillAddButton)
        val drillVideo = findViewById<ImageView>(R.id.drillVideo)
        val index = intent.getIntExtra("Index", 0)
        val context = intent.getStringExtra("Activity")
        var drill : Drill
        var totalRating : Double = 0.0

        if (context == "MainActivity") {
            addButton.visibility = View.INVISIBLE
            drill = drills[index]
        } else if(context == "ChooseDrillActivity") {
            drill = drills[index]
        } else {
            drill = DataManager.chosenDrills[index]
        }

        drill.rating?.forEach { (key, value) ->
            totalRating += value
        }

        drillRating.text = (totalRating/ drill.rating?.size!!.toDouble()).toString()
        viewButton.alpha = 0.5F
        drillTitle.text = drill.name
        drillContent.text = drill.content

        addButton.setOnClickListener {
            DataManager.chosenDrills.add(drill)
            val intent = Intent(this, PracticePlannerActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun drillsFromDatabase() {
        val drillsRef = db.collection("drills")
        drillsRef.addSnapshotListener { snapshot, e ->
            if(snapshot != null) {
                drills.clear()
                for(document in snapshot.documents) {
                    val newDrill = document.toObject(Drill::class.java)
                    val message = newDrill!!.name
                    if (newDrill != null)
                        drills.add(newDrill!!)
                }
            }
        }
    }
}
