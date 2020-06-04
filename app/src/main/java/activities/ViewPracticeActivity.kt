package activities

import adapters.PracticeRecyclerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.*
import android.widget.Adapter
import androidx.recyclerview.widget.*
import com.creativeleague.drillable.*

class ViewPracticeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_practice)
        val teamName = intent.getStringExtra("Team")
        var chosenTeam : Team = Team()
        DataManager.userTeams.forEach { team ->
            if (team.name == teamName) {
                chosenTeam = team
            }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.practiceRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        Log.i("PRACTICES", "${chosenTeam.practices[0].length} ${chosenTeam.practices[0].participants} ${chosenTeam.practices[0].waterBreaks}")
        val adapter: PracticeRecyclerAdapter
        adapter = PracticeRecyclerAdapter(this, chosenTeam.practices[0].drills)
        recyclerView.adapter = adapter

    }
}
