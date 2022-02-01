package com.pradesh.googleadmobinterstititalads

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd

class MainActivity : AppCompatActivity() {
    private var mLoadAdButton: Button? = null
    private var mInterstitialAd: InterstitialAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd()
        loadInterstitial()

        // Create the load ad button, tries to show an interstitial when clicked.
        mLoadAdButton = findViewById(R.id.load_ad_button) as Button
        mLoadAdButton!!.isEnabled = false
        mLoadAdButton!!.setOnClickListener {
            showInterstitial()
        }
    }

    private fun newInterstitialAd(): InterstitialAd {
        var interstitialAd = InterstitialAd(this)
        interstitialAd.adUnitId = getString(R.string.interstitial_ad_unit_id)
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                mLoadAdButton!!.isEnabled = true
                Toast.makeText(applicationContext, "Ad Loaded", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                mLoadAdButton!!.isEnabled = true
                Toast.makeText(applicationContext, "Ad Failed To Load", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                // Proceed to the next level.
                // goToNextLevel()
                Toast.makeText(applicationContext, "Ad Closed", Toast.LENGTH_SHORT).show()
                tryToLoadAdOnceAgain()
            }
        }
        return interstitialAd
    }

    private fun loadInterstitial() {
        // Disable the load ad button and load the ad.
        mLoadAdButton!!.isEnabled = false
        val adRequest = AdRequest.Builder().build()
        mInterstitialAd!!.loadAd(adRequest)
    }

    private fun showInterstitial() {
        // Show the ad if it is ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd!!.isLoaded) {
            mInterstitialAd!!.show()
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show()
            tryToLoadAdOnceAgain()
        }
    }

    private fun tryToLoadAdOnceAgain() {
        mInterstitialAd = newInterstitialAd()
        loadInterstitial()
    }
}