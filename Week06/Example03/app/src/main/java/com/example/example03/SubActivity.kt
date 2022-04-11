package com.example.example03

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        var tv_name_age: TextView = findViewById(R.id.tv_name_age)
        var tv_phone: TextView = findViewById(R.id.tv_phone)
        var tv_address: TextView = findViewById(R.id.tv_address)
        var tv_etc: TextView = findViewById(R.id.tv_etc)

        tv_name_age.text = intent.getStringExtra("name") + ", " + intent.getStringExtra("age")
        tv_phone.text = intent.getStringExtra("phone")
        tv_address.text = intent.getStringExtra("address")
        tv_etc.text = intent.getStringExtra("etc")

        var btn: Button = findViewById(R.id.btn_sub)
        btn.setOnClickListener {
            finish()
        }
    }
}