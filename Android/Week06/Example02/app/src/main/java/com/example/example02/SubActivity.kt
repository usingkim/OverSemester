package com.example.example02

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        var btn: Button = findViewById(R.id.btn_sub)
        var ev: EditText = findViewById(R.id.ev_sub)
        var res: TextView = findViewById(R.id.res_sub)
        res.text = intent.getStringExtra("data") + "\n-send from main"

        btn.setOnClickListener{
            if (ev.text.isEmpty()){
                Toast.makeText(this.getApplicationContext(),"Edit Text가 비었습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val data: String = ev.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", data)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}