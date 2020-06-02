package com.creativeleague.drillable

import adapters.DrillRecyclerAdapter
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


val db = FirebaseFirestore.getInstance()
val auth = FirebaseAuth.getInstance()
var userTeams = mutableListOf<Team>()

object DataManager {
    val drills = mutableListOf<Drill>(
        Drill( name = "Shooting", length = 20, content = "Shoot the ball hard", rating = mutableMapOf("Hej" to 3)),
        Drill(name = "Defense", length = 15, content = "Play tough defense with a lot of grit like Kobe Bryant", rating = mutableMapOf("Hej" to 3))
    )
    val chosenDrills = mutableListOf<Drill>()

    init {
        /*val newDrill =  Drill("Name", 15, "Content.", mutableMapOf("UserID" to 1))
        db.collection("drills")
            .add(newDrill)
            .addOnSuccessListener { documentReference ->
                Log.d("DRILL ADDED TO DATABASE", "DocumentSnapshot added with ID: " + documentReference.id)
            }
            .addOnFailureListener { e ->
                Log.w("FAILED TO ADD DRILL", "Error adding document", e)
            }*/
        //drillsFromDatabase()
    }
    /*
    private fun drillsFromDatabase() {
        val drillsRef = db.collection("drills")
        drillsRef.addSnapshotListener { snapshot, e ->
            if(snapshot != null) {
                drills.clear()
                for(document in snapshot.documents) {
                    val newDrill = document.toObject(Drill::class.java)
                    val message = newDrill!!.name
                    if (newDrill != null)
                        drills.add(newDrill!!)
                }
                val drillRecyclerAdapter = DrillRecyclerAdapter
                DrillRecyclerAdapter().notifyDataSetChanged()
            }
        }
    }*/
}