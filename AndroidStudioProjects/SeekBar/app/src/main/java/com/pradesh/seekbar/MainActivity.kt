package com.pradesh.seekbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var seekBarNormal: SeekBar? = null
    var seekBarDiscrete: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarNormal= findViewById(R.id.seekbar_Default)
        seekBarDiscrete = findViewById(R.id.seekbar_Discrete)

        seekBarNormal?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Toast.makeText(applicationContext, "seekbar progress: $progress", Toast.LENGTH_LONG).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch started!", Toast.LENGTH_LONG).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //Toast.makeText(applicationContext, "seekbar touch stopped!", Toast.LENGTH_LONG).show()
            }
        })

        seekBarDiscrete?.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Toast.makeText(applicationContext, "discrete seekbar progress: $progress", Toast.LENGTH_LONG).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                //Toast.makeText(applicationContext, "discrete seekbar touch started!", Toast.LENGTH_LONG).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                //Toast.makeText(applicationContext, "discrete seekbar touch stopped", Toast.LENGTH_LONG).show()
            }
        })
    }
}