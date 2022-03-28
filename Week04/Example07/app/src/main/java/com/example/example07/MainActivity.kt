package com.example.example07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import androidx.core.view.isInvisible

class MainActivity : AppCompatActivity(){
    lateinit var left: Button
    lateinit var right: Button
    lateinit var img: ImageView
    var c = 'a'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "봉봉이"

        left = findViewById<Button>(R.id.left)
        right = findViewById<Button>(R.id.right)
        img = findViewById<ImageView>(R.id.imgView)

        left.setOnClickListener {
            var prev = c - 1
            if (prev < 'a') prev = 't'
            img.setImageResource(R.drawable.t)
        }

        right.setOnClickListener {
            var next = c + 1
            if (next > 't') next = 'a'
        }

    }
}