package com.creativeleague.drillable

import com.google.firebase.firestore.FirebaseFirestore

object DataManager {
    val db = FirebaseFirestore.getInstance()
    val drills = mutableListOf<Drill>()
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