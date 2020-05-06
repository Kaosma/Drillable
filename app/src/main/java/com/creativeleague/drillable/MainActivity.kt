package com.creativeleague.drillable

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.drillRecyclerView)
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
