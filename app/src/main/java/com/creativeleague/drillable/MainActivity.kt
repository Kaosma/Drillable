package com.creativeleague.drillable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var drills = listOf<Drill>(Drill("Dribbling", 10, "Dribble the ball hard", 4.0),
            Drill("Passing", 25, "Pass the ball hard", 2.0),
            Drill("Shooting", 15, "Shoot the ball hard", 5.0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.drillRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = DrillRecyclerAdapter(this, drills)

        recyclerView.adapter = adapter
    }
}
