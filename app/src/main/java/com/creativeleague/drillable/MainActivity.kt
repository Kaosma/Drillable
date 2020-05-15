package com.creativeleague.drillable

import adapters.FragmentAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import fragments.ClipBoardFragment
import fragments.DrillBankFragment
import fragments.TeamsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab = findViewById<View>(R.id.planPracticeFab)
        val tabView = findViewById<TabLayout>(R.id.tabView)
        val fragmentContainer =findViewById<ViewPager>(R.id.fragmentContainer)
        var adapter = FragmentAdapter(this, supportFragmentManager)

        fragmentContainer.adapter = adapter
        tabView.setupWithViewPager(fragmentContainer)
        tabView.getTabAt(0)?.setIcon(R.drawable.ic_basketball_eggwhite_24dp)
        tabView.getTabAt(1)?.setIcon(R.drawable.ic_team_eggwhite_24dp)
        tabView.getTabAt(2)?.setIcon(R.drawable.ic_clipboard_eggwhite_24dp)

        fab.setOnClickListener { view ->
            val intent = Intent(this, PracticePlannerActivity::class.java)
            startActivity(intent)
        }
//        tabView.
//        tabView.setOnClickListener {
//            if(tabView.selectedTabPosition == 0) {
//                Log.i("HEJHEJ", "DRILLDRILL")
//            } else if(tabView.selectedTabPosition == 1) {
//                Log.i("HEJHEJ", "TEAMTEAM")
//            } else if(tabView.selectedTabPosition == 2) {
//                Log.i("HEJHEJ", "CLIPCLIP")
//            }
//            Log.i("HEJ", "NEJ")
//        }

    }
    fun enableSearchFab() {

    }
}
