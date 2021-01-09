package com.eunidev.belajarbuatintroslider

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private lateinit var preferencesManager: PreferencesManager
    private var onBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        preferencesManager = PreferencesManager(applicationContext)

        val autDes = "Author        : EuniDev\n " +
                     "Author IG     : @re.vel_\n" +
                     "Author Github : kafri8889\n" +
                     "Source Code   : https://github.com/kafri8889/IntroSliderExampleAndroid"
        findViewById<TextView>(R.id.textViewDes).text = autDes

        findViewById<Button>(R.id.buttonBackToWelcomeScreen_MainActivity).setOnClickListener {
            preferencesManager.setFirstLaunchApp(true)
            startActivity(Intent(this, WelcomeScreenActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        if (onBack) {
            finish()
        } else {
            Toast.makeText(this, "Teken 2x Buat Keluar", Toast.LENGTH_SHORT).show();
            object : CountDownTimer(2500, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    onBack = true
                    Log.i("CountdownTimer", millisUntilFinished.toString())
                }

                override fun onFinish() {
                    onBack = false
                }
            }
        }
    }
}