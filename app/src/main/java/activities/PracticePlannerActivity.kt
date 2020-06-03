package activities

import adapters.PracticePlannerRecyclerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.creativeleague.drillable.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PracticePlannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_planner)

        val recyclerView = findViewById<RecyclerView>(R.id.practicePlannerRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PracticePlannerRecyclerAdapter(this,
            DataManager.chosenDrills
        )
        recyclerView.adapter = adapter

        val addDrillFab = findViewById<FloatingActionButton>(R.id.addDrillFab)
        addDrillFab.setOnClickListener { view ->
            val intent = Intent(this, ChooseDrillActivity::class.java)
            this.startActivity(intent)
        }
        val settingsFab = findViewById<FloatingActionButton>(R.id.practiceSettingsFab)
        settingsFab.setOnClickListener {
            val intent = Intent(this, PracticeSetupActivity::class.java)
            this.startActivity(intent)
        }
    }



    /*fun rateDrill(drill: Drill) {
        if(!drillIstRated(drill)) {

        }

        db.collection("drills")
    }*/
}