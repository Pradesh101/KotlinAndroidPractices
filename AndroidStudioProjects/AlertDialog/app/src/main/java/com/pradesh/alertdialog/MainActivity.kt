package com.pradesh.alertdialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            //set title of alert dialog box
            builder.setTitle(R.string.title)
            //set message for dialog box
            builder.setMessage(R.string.dialogMsg)
            builder.setIcon(R.drawable.ic_baseline_add_alert_24)

            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface,which ->
                Toast.makeText(this,"Clicked Yes",Toast.LENGTH_LONG).show()
            }
            //performing neutral action
            builder.setNeutralButton("Cancel") {dialogInterface, which ->
                Toast.makeText(this,"Clicked Cancel\n Operation Cancel",Toast.LENGTH_LONG).show()
            }
            //performing negative action
            builder.setNegativeButton("No") {dialogInterface, which ->
                Toast.makeText(this,"Clicked No",Toast.LENGTH_LONG).show()
            }

            //creating dialog box
            val alertDialog: AlertDialog = builder.create()
            //set other alertDialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}