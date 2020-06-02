package activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import com.creativeleague.drillable.*
import kotlinx.android.synthetic.main.activity_practice_setup.*

class PracticeSetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_setup)
        val teamSpinnerList = mutableListOf<String>()
        userTeams.forEach { team ->
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
                Toast.makeText(this@PracticeSetupActivity, "$item selected", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
