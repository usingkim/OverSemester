package com.example.example07

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(){
    lateinit var key_text : TextView
    lateinit var num1 : Button
    lateinit var num2 : Button
    lateinit var num3 : Button
    lateinit var num4 : Button
    lateinit var num5 : Button
    lateinit var num6 : Button
    lateinit var num7 : Button
    lateinit var num8 : Button
    lateinit var num9 : Button
    lateinit var num_star : Button
    lateinit var num0 : Button
    lateinit var num_sharp : Button
    lateinit var back : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num1 = findViewById<Button>(R.id.num1)
        num2 = findViewById<Button>(R.id.num2)
        num3 = findViewById<Button>(R.id.num3)
        num4 = findViewById<Button>(R.id.num4)
        num5 = findViewById<Button>(R.id.num5)
        num6 = findViewById<Button>(R.id.num6)
        num7 = findViewById<Button>(R.id.num7)
        num8 = findViewById<Button>(R.id.num8)
        num9 = findViewById<Button>(R.id.num9)
        num0 = findViewById<Button>(R.id.num0)
        num_star = findViewById<Button>(R.id.num_star)
        num_sharp = findViewById<Button>(R.id.num_sharp)

        key_text = findViewById<TextView>(R.id.key_text)

        back = findViewById<ImageView>(R.id.back)

        key_text.text = ""

        fun addNumber(num: String){
            if (key_text.length() == 3 || key_text.length() == 8){
                key_text.text = key_text.text.toString() + "-" + num
            }
            else
                key_text.text = key_text.text.toString() + num
        }

        num1.setOnClickListener {
            addNumber("1")
        }

        num2.setOnClickListener {
            addNumber("2")
        }

        num3.setOnClickListener {
            addNumber("3")
        }

        num4.setOnClickListener {
            addNumber("4")
        }

        num5.setOnClickListener {
            addNumber("5")
        }

        num6.setOnClickListener {
            addNumber("6")
        }

        num7.setOnClickListener {
            addNumber("7")
        }

        num8.setOnClickListener {
            addNumber("8")
        }

        num9.setOnClickListener {
            addNumber("9")
        }

        num0.setOnClickListener {
            addNumber("0")
        }

        num_sharp.setOnClickListener {
            addNumber("#")
        }

        num_star.setOnClickListener {
            addNumber("*")
        }

        back.setOnClickListener{
            var pre = key_text.text.toString()
            var pre_len = key_text.length()

            if (pre_len > 0) {
                if (pre_len >= 2 && pre[pre_len - 2] == '-')
                    key_text.text = pre.substring(0, pre_len - 2)
                else
                    key_text.text = pre.substring(0, pre_len - 1)
            }
        }

    }
}