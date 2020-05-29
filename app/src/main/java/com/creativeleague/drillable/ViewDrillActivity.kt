package com.creativeleague.drillable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.drill_item.*
import org.w3c.dom.Text

class ViewDrillActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_drill)
        val drillTitle = findViewById<TextView>(R.id.drillTitle)
        val drillContent = findViewById<TextView>(R.id.drillContent)
        val drillRating = findViewById<TextView>(R.id.drillRating)
        val rateButton = findViewById<Button>(R.id.drillRateButton)
        val viewButton = findViewById<Button>(R.id.drillViewButton)
        val addButton = findViewById<Button>(R.id.drillAddButton)
        val drillVideo = findViewById<ImageView>(R.id.drillVideo)
        val index = intent.getIntExtra("Index", 0)
        val context = intent.getStringExtra("Activity")
        val drill = DataManager.drills[index]
        var totalRating : Double = 0.0

        if (context=="MainActivity") {
            addButton.visibility = View.INVISIBLE
        }

        drill.rating.forEach { (key, value) ->
            totalRating += value
        }
        
        drillRating.text = (totalRating/drill.rating.size.toDouble()).toString()
        viewButton.alpha = 0.5F
        drillTitle.text = drill.name
        drillContent.text = drill.content

        addButton.setOnClickListener {
            DataManager.chosenDrills.add(drill)
            val intent = Intent(this, PracticePlannerActivity::class.java)
            this.startActivity(intent)
        }
    }
}
