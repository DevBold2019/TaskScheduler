package com.example.schedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val handle:Handler= Handler()

        handle.postDelayed({
            val intent:Intent= Intent(this@SplashActivity,WalkThroughActivity::class.java)
            startActivity(intent)
            finish()
        },4500)


    }
}
