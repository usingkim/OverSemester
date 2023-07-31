package com.example.example06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.isInvisible

class MainActivity : AppCompatActivity(){
    lateinit var b1: Button
    lateinit var b2: Button
    lateinit var b3: Button
    lateinit var b4: Button
    lateinit var b5: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "직접해보기 6번 문제"

        b1 = findViewById<Button>(R.id.b1)
        b2 = findViewById<Button>(R.id.b2)
        b3 = findViewById<Button>(R.id.b3)
        b4 = findViewById<Button>(R.id.b4)
        b5 = findViewById<Button>(R.id.b5)

        b1.setOnClickListener {
            b1.visibility = View.INVISIBLE
            b2.visibility = View.VISIBLE
        }

        b2.setOnClickListener {
            b2.visibility = View.INVISIBLE
            b3.visibility = View.VISIBLE
        }

        b3.setOnClickListener {
            b3.visibility = View.INVISIBLE
            b4.visibility = View.VISIBLE
        }

        b4.setOnClickListener {
            b4.visibility = View.INVISIBLE
            b5.visibility = View.VISIBLE
        }

        b5.setOnClickListener {
            b5.visibility = View.INVISIBLE
            b1.visibility = View.VISIBLE
        }
    }
}