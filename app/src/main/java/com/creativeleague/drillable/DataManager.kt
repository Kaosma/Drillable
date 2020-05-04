package com.creativeleague.drillable

object DataManager {
    val drills = mutableListOf<Drill>()
    init {
        drillsFromDatabase()
    }

    private fun drillsFromDatabase() {
        var drill = Drill("Dribbling", 10, "Dribble the ball hard", 4.0)
        drills.add(drill)
        drill = Drill("Passing", 25, "Pass the ball hard", 2.0)
        drills.add(drill)
        drill = Drill("Shooting", 15, "Shoot the ball hard", 5.0)
        drills.add(drill)
    }
}