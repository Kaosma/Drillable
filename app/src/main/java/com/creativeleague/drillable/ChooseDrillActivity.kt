package com.creativeleague.drillable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChooseDrillActivity : AppCompatActivity() {
//    var addButton = findViewById<Button>(R.id.addButton)
//    var rateButton = findViewById<Button>(R.id.rateButton)
//    var viewButton = findViewById<Button>(R.id.viewButton)
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_drill)

        recyclerView = findViewById<RecyclerView>(R.id.addDrillRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DrillRecyclerAdapter(this, DataManager.drills)
        recyclerView.adapter = adapter
    }
}
