package com.example.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        nextActivity()
    }

    private fun nextActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LogIn::class.java))
            finish()
        }, 3000)
    }
}