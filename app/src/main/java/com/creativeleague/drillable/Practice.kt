package com.creativeleague.drillable

class Practice (var drills: MutableList<Drill> = mutableListOf(), var participants: Int? = null, var waterBreaks: Int? = null, var length: Int = 0) {
    fun updateLength() {
        drills.forEach { drill ->
            length += drill.length
        }
    }
}