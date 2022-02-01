package com.pradesh.googleadmobbannerads

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class MainActivity : AppCompatActivity() {
    lateinit var adView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Load an ad into the AdMob banner view.
        adView = findViewById<View>(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.adListener = object : AdListener(){
            fun onAdFailedToLoad(p0: Int) {
                super.onAdFailedToLoad(p0)
                val toastMessage: String = "ad fail to load"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG).show()
            }
            override fun onAdLoaded() {
                super.onAdLoaded()
                val toastMessage: String = "ad loaded"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG).show()
            }
            override fun onAdOpened() {
                super.onAdOpened()
                val toastMessage: String = "ad is open"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG).show()
            }
            override fun onAdClicked() {
                super.onAdClicked()
                val toastMessage: String = "ad is clicked"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onAdClosed() {
                super.onAdClosed()
                val toastMessage: String = "ad is closed"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG).show()
            }
            override fun onAdImpression() {
                super.onAdImpression()
                val toastMessage: String = "ad impression"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG).show()
            }
            @SuppressLint("MissingSuperCall")
            override fun onAdLeftApplication() {
                super.onAdLeftApplication()
                val toastMessage: String = "ad left application"
                Toast.makeText(applicationContext, toastMessage.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPause() {
        if (adView!=null) {
            adView.pause();
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        if (adView != null) {
            adView.resume();
        }
    }

    override fun onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}