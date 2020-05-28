package com.creativeleague.drillable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
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
        val drill = DataManager.drills[index]
        var totalRating : Double = 0.0
        var counter = 0

        viewButton.alpha = 0.5F
        drillTitle.text = drill.name
        drillContent.text = drill.content

        for (rating in drill.rating) {
            //totalRating += rating[counter]!!
            counter += 1
        }
        //drillRating.text = (totalRating/drill.rating.size.toDouble()).toString()
        addButton.setOnClickListener {
            DataManager.chosenDrills.add(drill)
            val intent = Intent(this, PracticePlannerActivity::class.java)
            this.startActivity(intent)
        }
    }
}
