package com.pradesh.bhaktapursales.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.pradesh.bhaktapursales.ui.R
import kotlinx.android.synthetic.main.activity_splash_activity.*

class SplashActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_activity)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
            //Launch the MainActivity
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()//Call this when your activity is done and should be closed
            },
            3000
        )

//        val typeface: Typeface = Typeface.createFromAsset(assets,"Lonely.ttf")
//        tv_app_name.typeface=typeface
//        tv_app_sub.typeface=typeface
    }

}