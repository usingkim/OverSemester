package com.example.example05

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var linLayer: LinearLayout
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "직접해보기 1번 문제"

        button = findViewById<Button>(R.id.button)
        linLayer = findViewById<LinearLayout>(R.id.LinLay)

        button.setOnClickListener {
            var c: String = "#FF0000"

            if (count % 3 == 0) c = "#00FF00"
            else if (count % 3 == 1) c = "#0000FF"
            else if (count % 3 == 2) c = "#FF0000"
            linLayer.setBackgroundColor(Color.parseColor(c))
            count += 1
            false
        }
    }
}