package com.creativeleague.drillable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var rateButton: Button
    lateinit var addButton: Button
    lateinit var viewButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        addButton = findViewById(R.id.addButton)
//        rateButton = findViewById(R.id.rateButton)
//        viewButton = findViewById(R.id.viewButton)
//        addButton.isEnabled = false
//        addButton.isClickable = false
        val recyclerView = findViewById<RecyclerView>(R.id.drillRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DrillRecyclerAdapter(this, DataManager.drills)
        recyclerView.adapter = adapter

        val fab = findViewById<View>(R.id.planPracticeFab)
        fab.setOnClickListener { view ->
            val intent = Intent(this, PracticePlannerActivity::class.java)
            startActivity(intent)
        }
    }
}
