package com.example.example03

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

        var btn: Button = findViewById(R.id.btn_confirm)
        btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            setResult(11, intent)
            Toast.makeText(this.getApplicationContext(),"저장되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        var btn2: Button = findViewById(R.id.btn_revise)
        btn2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            setResult(12, intent)
            finish()
        }


    }
}