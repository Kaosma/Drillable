package activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import com.creativeleague.drillable.*
import com.creativeleague.drillable.DataManager.chosenDrills
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_practice_setup.*

class PracticeSetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_setup)
        val doneFab = findViewById<FloatingActionButton>(R.id.practiceDoneFab)
        val teamSpinnerList = mutableListOf<String>()
        lateinit var chosenTeam : Team
        DataManager.userTeams.forEach { team ->
            teamSpinnerList.add(team.name)
        }
        val adapter : ArrayAdapter<String> = ArrayAdapter(this,
            R.layout.support_simple_spinner_dropdown_item, teamSpinnerList)

        teamSpinner.adapter = adapter

        teamSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item : String = teamSpinnerList[position]
                DataManager.userTeams.forEach { team ->
                    if (team.name == item) {
                        chosenTeam = team
                    }
                }
                Toast.makeText(this@PracticeSetupActivity, "$item selected", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        doneFab.setOnClickListener {
            val intent = Intent(this, ViewPracticeActivity::class.java)
            val practice = Practice(chosenDrills)
            practice.updateLength()


            DataManager.userTeams.forEach { team ->
                if (team.name == chosenTeam.name) {
                    team.practices.add(practice)
                }
            }
            intent.putExtra("Team", chosenTeam.name)

            this.startActivity(intent)
            chosenDrills = mutableListOf()
        }
    }
}
