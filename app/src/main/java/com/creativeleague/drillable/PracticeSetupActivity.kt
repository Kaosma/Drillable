package com.creativeleague.drillable

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_practice_setup.*
import java.lang.reflect.Field

class PracticeSetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_setup)
        val listForNow : MutableList<String> = ArrayList()
        for (i : Int in 1..1000)
            listForNow.add("Team $i")
        val adapter : ArrayAdapter<String> = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listForNow)
        teamSpinner.adapter = adapter
        teamSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item : String = listForNow[position]
                Toast.makeText(this@PracticeSetupActivity, "$item selected", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        //limitSpinnerHeight(teamSpinner)
    }
    /*fun limitSpinnerHeight(spinner: Spinner) {
        val popup = Spinner.class.getDeclaredField("popup")
        popup.isAccessible = true

        val popupWindow : ListPopupWindow = popup.get(teamSpinner) as ListPopupWindow
        popupWindow.height = (200 * resources.displayMetrics.density).toInt()
    }*/
}
