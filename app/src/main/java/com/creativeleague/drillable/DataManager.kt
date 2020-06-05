package com.creativeleague.drillable

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataManager {
    var chosenDrills = mutableListOf<Drill>()
    var userTeams = mutableListOf<Team>()
    /*
    fun loadUserTeam(context: Context) {
        val sharedPreferences = context.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = sharedPreferences.getString("USER_TEAMS", null)
        val type = object : TypeToken<MutableList<Team>>().type
        val teams = gson.fromJson<MutableList<Team>>(json, type)
        userTeams = teams
    }*/
    fun saveUserTeam(team: Team, context: Context) {
        //shared preferences (Name, Mode)
        val sharedPreferences = context.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        userTeams.add(team)
        val json = gson.toJson(userTeams)
        editor.putString("USER_TEAMS", json)
        editor.apply()
    }
}