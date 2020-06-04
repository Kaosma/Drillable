package activities

import adapters.DrillRecyclerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.creativeleague.drillable.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import fragments.DrillBankFragment

class ChooseDrillActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    val drills = mutableListOf<Drill>()
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_drill)
        drillsFromDatabase()
        recyclerView = findViewById<RecyclerView>(R.id.addDrillRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DrillRecyclerAdapter(this, drills)
        recyclerView.adapter = adapter
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
                        newDrill.id = document.id
                        drills.add(newDrill!!)
                }
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }
}