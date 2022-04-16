package com.example.example05

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        var tv_k_name: TextView = findViewById(R.id.tv_k_name)
        var tv_name: TextView = findViewById(R.id.tv_name)
        var tv_phone: TextView = findViewById(R.id.tv_phone)
        var tv_email: TextView = findViewById(R.id.tv_email)
        var tv_address: TextView = findViewById(R.id.tv_address)
        var sub_image: ImageView = findViewById(R.id.sub_image)

        var btn_resume: Button = findViewById(R.id.btn_resume)

        tv_k_name.text = intent.getStringExtra("k_name")
        tv_name.text = intent.getStringExtra("name")
        tv_phone.text = intent.getStringExtra("phone")
        tv_email.text = intent.getStringExtra("email")
        tv_address.text = intent.getStringExtra("address")
        sub_image.setImageBitmap(intent.getParcelableExtra("image"))

        btn_resume.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            setResult(12, intent)
            finish()
        }

    }
}