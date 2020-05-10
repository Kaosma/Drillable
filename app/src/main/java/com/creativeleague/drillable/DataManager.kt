package com.creativeleague.drillable

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

val db = FirebaseFirestore.getInstance()
val auth = FirebaseAuth.getInstance()

object DataManager {
    val drills = mutableListOf<Drill>()
    val chosenDrills = mutableListOf<Drill>(Drill("HEJ", 10, "du", 4.0),Drill("bajs", 15, "på", 4.5),Drill("Nej", 5, "åh", 3.0))
    init {
        drillsFromDatabase()
    }

    private fun drillsFromDatabase() {
        val drillsRef = db.collection("drills")
        drillsRef.addSnapshotListener { snapshot, e ->
            if(snapshot != null) {
                drills.clear()
                for(document in snapshot.documents) {
                    val newDrill = document.toObject(Drill::class.java)
                    if(newDrill != null)
                        drills.add(newDrill!!)
                }
            }
        }
    }
}