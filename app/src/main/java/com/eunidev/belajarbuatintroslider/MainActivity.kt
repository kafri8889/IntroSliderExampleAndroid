package com.eunidev.belajarbuatintroslider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var preferencesManager: PreferencesManager
    private var onBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferencesManager = PreferencesManager(applicationContext)

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