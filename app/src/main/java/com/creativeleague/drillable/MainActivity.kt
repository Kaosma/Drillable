package com.creativeleague.drillable

import adapters.FragmentAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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

        fab.setOnClickListener { view ->
            val intent = Intent(this, PracticePlannerActivity::class.java)
            startActivity(intent)
        }
    }
    fun setDrillTab(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        val drillBankFragment = DrillBankFragment()
        transaction.replace(R.id.fragmentContainer, drillBankFragment, "drillBankFragment")
        transaction.commit()
    }
    fun setTeamsTab(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        val teamsFragment = TeamsFragment()
        transaction.replace(R.id.fragmentContainer, teamsFragment, "teamsFragment")
        transaction.commit()
    }
    fun setClipBoardTab(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        val clipBoardFragment = ClipBoardFragment()
        transaction.replace(R.id.fragmentContainer, clipBoardFragment, "clipBoardFragment")
        transaction.commit()
    }
}
