package com.creativeleague.drillable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PracticePlannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_planner)

        val recyclerView = findViewById<RecyclerView>(R.id.practiceRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PracticeRecyclerAdapter(this, DataManager.chosenDrills)
        recyclerView.adapter = adapter

        val fab = findViewById<View>(R.id.addDrillFab)
        fab.setOnClickListener { view ->
            val intent = Intent(this, ChooseDrillActivity::class.java)
            startActivity(intent)
        }
    }

    fun addDrill(drill: Drill) {
        DataManager.chosenDrills.add(drill)
    }

    fun rateDrill(drill: Drill) {
//        val user = auth.currentUser
//        if (user == null)
//            return
//
//        db.collection("users").document(user!!.uid).collection("drills")
    }

    fun viewDrill() {

    }
}