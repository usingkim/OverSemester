package com.example.example05

import android.content.Intent
import android.os.Bundle
import android.widget.*
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

        var et_core: EditText = findViewById(R.id.et_core)
        var et_mbti: EditText = findViewById(R.id.et_mbti)
        var et_student: EditText = findViewById(R.id.et_student)
        var et_grow: EditText = findViewById(R.id.et_grow)

        tv_k_name.text = intent.getStringExtra("k_name")
        tv_name.text = intent.getStringExtra("name")
        tv_phone.text = intent.getStringExtra("phone")
        tv_email.text = intent.getStringExtra("email")
        tv_address.text = intent.getStringExtra("address")
        sub_image.setImageBitmap(intent.getParcelableExtra("image"))

        et_core.setText(intent.getStringExtra("et_core"))
        et_mbti.setText(intent.getStringExtra("et_mbti"))
        et_student.setText(intent.getStringExtra("et_student"))
        et_grow.setText(intent.getStringExtra("et_grow"))

        btn_resume.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("et_core", et_core.text.toString())
            intent.putExtra("et_mbti", et_mbti.text.toString())
            intent.putExtra("et_student", et_student.text.toString())
            intent.putExtra("et_grow", et_grow.text.toString())

            setResult(12, intent)
            finish()
        }

    }
}