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
        val playerSeekBar = findViewById<SeekBar>(R.id.playerSeekBar)
        val minuteSeekBar = findViewById<SeekBar>(R.id.minuteSeekBar)
        val waterBreakSeekBar = findViewById<SeekBar>(R.id.waterBreakSeekBar)
        var chosenTeam : Team? = null
        var numberOfPlayers = 0
        var practiceLength = 0
        var waterBreaks = 0
        val adapter : ArrayAdapter<String> = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, teamSpinnerList)
        teamSpinner.adapter = adapter
        DataManager.userTeams.forEach { team ->
            teamSpinnerList.add(team.name)
        }

        playerSeekBar.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                playerAmountText.text = "$progress"
                numberOfPlayers = progress
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        minuteSeekBar.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val interval = progress*5
                minuteAmountText.text = "$interval min"
                practiceLength = interval
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        waterBreakSeekBar.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                waterBreakAmountText.text = "$progress"
                waterBreaks = progress
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        teamSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
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
            val practice = Practice(chosenDrills, numberOfPlayers, waterBreaks, practiceLength)
            practice.updateLength()

            DataManager.userTeams.forEach { team ->
                if (team.name == chosenTeam?.name) {
                    team.practices.add(practice)
                }
            }
            intent.putExtra("Team", chosenTeam?.name)

            this.startActivity(intent)
            chosenDrills = mutableListOf()
        }
    }
}
