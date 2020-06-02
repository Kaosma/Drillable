package activities

import adapters.DrillRecyclerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.creativeleague.drillable.*

class ChooseDrillActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_drill)

        recyclerView = findViewById<RecyclerView>(R.id.addDrillRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DrillRecyclerAdapter(this,
            DataManager.drills
        )
        recyclerView.adapter = adapter
    }
}