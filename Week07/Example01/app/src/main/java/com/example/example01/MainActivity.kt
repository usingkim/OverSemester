package com.example.example01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragButton = findViewById<Button>(R.id.fragBut1)
        val fragmentManager: FragmentManager = supportFragmentManager
        var onClicked = false

        fragButton.setOnClickListener {
            if (onClicked){
                onClicked = false
                val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                val frameLayout = supportFragmentManager.findFragmentById(R.id.fragment_content)
                transaction.remove(frameLayout!!).commit()
            }
            else{
                onClicked = true
                val transaction: FragmentTransaction = fragmentManager.beginTransaction()
                transaction.add(R.id.fragment_content, OneFragment()).commit()
            }
        }
    }
}