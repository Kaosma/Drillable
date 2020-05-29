package com.creativeleague.drillable

import android.util.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

val db = FirebaseFirestore.getInstance()
val auth = FirebaseAuth.getInstance()

object DataManager {
    val drills = mutableListOf<Drill>(
        Drill(name = "Shooting", length = 20, content = "Shoot the ball hard", rating =
            mutableMapOf("Hej" to 3)),
        Drill(name = "Defense", length = 15, content = "Play tough defense with a lot of grit like Kobe Bryant", rating = mutableMapOf("Hej" to 3))
    )
    val chosenDrills = mutableListOf<Drill>()

    /*init {
        drillsFromDatabase()
    }

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
                //adapter.notifyDataSetChanged
            }
        }
    }*/
}