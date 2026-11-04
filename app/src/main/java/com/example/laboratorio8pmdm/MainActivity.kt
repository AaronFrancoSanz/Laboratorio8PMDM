package com.example.laboratorio8pmdm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnIrForja).setOnClickListener {
            startActivity(Intent(this, Laboratorio3Activity::class.java))
        }
        findViewById<Button>(R.id.btnIrVideo).setOnClickListener {
            startActivity(Intent(this, VideoActivity::class.java))
        }
    }
}
