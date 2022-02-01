package com.example.dinnerdecider

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val foodList = arrayListOf("Chinese","Mexican","Newari","Indian","Continental","Korean")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        decideBtn.setOnClickListener(){
            val random = Random()
            val randomFood = random.nextInt(foodList.count())
            selectedFoodTxt.text=foodList[randomFood]
        }

        addFoodBtn.setOnClickListener(){
            val newFood = addFoodTxt.text.toString()
            foodList.add(newFood)
            addFoodTxt.text.clear()
            println(foodList)
        }

    }
}