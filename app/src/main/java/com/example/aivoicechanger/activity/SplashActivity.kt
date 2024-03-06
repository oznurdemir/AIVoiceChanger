package com.example.aivoicechanger.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import com.example.aivoicechanger.R

class SplashActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        progressBar = findViewById(R.id.progressBar)
        val handler = Handler()

        val runnable = object : Runnable {
            var progressStatus = 0

            override fun run() {
                progressStatus += 10
                progressBar.progress = progressStatus

                if (progressStatus >= 100) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                } else {
                    // Yarım saniyede bir güncelleme yapmak için 500 ms bekletiyoruz
                    handler.postDelayed(this, 500)
                }
            }
        }

        // İlk çağrıyı başlatmak için postDelayed kullanıyoruz
        handler.postDelayed(runnable, 0)
    }
}