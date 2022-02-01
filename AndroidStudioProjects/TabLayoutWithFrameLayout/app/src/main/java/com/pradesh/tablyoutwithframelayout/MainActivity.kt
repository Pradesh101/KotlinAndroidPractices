package com.pradesh.tablyoutwithframelayout

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    private var tabLayout: TabLayout? = null
    private var frameLayout: FrameLayout? = null
    var fragment: Fragment? = null
    var fragmentManager: FragmentManager? = null
    var fragmentTransaction: FragmentTransaction? = null
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        frameLayout = findViewById<FrameLayout>(R.id.frameLayout)

        fragment = JavaFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction!!.replace(R.id.frameLayout, fragment as JavaFragment)
        fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction!!.commit()
        //adding listener for tab select
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // creating cases for fragment
                when (tab.position) {
                    0 -> {
                        fragment = JavaFragment()
                        val fm = supportFragmentManager
                        val ft = fm.beginTransaction()
                        ft.replace(R.id.frameLayout, fragment as JavaFragment)
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ft.commit()
                    }
                    1 -> {
                        fragment = AndroidFragment()
                        val fm = supportFragmentManager
                        val ft = fm.beginTransaction()
                        ft.replace(R.id.frameLayout, fragment as AndroidFragment)
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ft.commit()
                    }
                    2 -> {
                        fragment = KotlinFragment()
                        val fm = supportFragmentManager
                        val ft = fm.beginTransaction()
                        ft.replace(R.id.frameLayout, fragment as KotlinFragment)
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ft.commit()
                    }
                    3 -> {
                        fragment = PhpFragment()
                        val fm = supportFragmentManager
                        val ft = fm.beginTransaction()
                        ft.replace(R.id.frameLayout, fragment as PhpFragment)
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ft.commit()
                    }
                    4 -> {
                        fragment = PythonFragment()
                        val fm = supportFragmentManager
                        val ft = fm.beginTransaction()
                        ft.replace(R.id.frameLayout, fragment as PythonFragment)
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        ft.commit()
                    }
                }
//                val fm = supportFragmentManager
//                val ft = fm.beginTransaction()
//                ft.replace(R.id.frameLayout, fragment)
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}