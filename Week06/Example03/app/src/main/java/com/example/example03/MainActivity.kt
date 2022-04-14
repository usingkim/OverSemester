package com.example.example03

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn : Button = findViewById(R.id.btn_main)
        var ev_name: EditText = findViewById(R.id.ev_name)
        var ev_age: EditText = findViewById(R.id.ev_age)
        var ev_phone: EditText = findViewById(R.id.ev_phone)
        var ev_address: EditText = findViewById(R.id.ev_address)
        var ev_etc: EditText = findViewById(R.id.ev_etc)

        btn.setOnClickListener{
            val name: String = ev_name.text.toString()
            val age: String = ev_age.text.toString()
            val phone: String = ev_phone.text.toString()
            val address: String = ev_address.text.toString()
            val etc: String = ev_etc.text.toString()

            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("age", age)
            intent.putExtra("phone", phone)
            intent.putExtra("address", address)
            intent.putExtra("etc", etc)
            startActivity(intent)

            ev_name.text.clear()
            ev_age.text.clear()
            ev_phone.text.clear()
            ev_address.text.clear()
            ev_etc.text.clear()
        }
    }

}