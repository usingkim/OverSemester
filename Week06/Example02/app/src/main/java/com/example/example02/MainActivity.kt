package com.example.example02

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
        var ev: EditText = findViewById(R.id.ev_main)


        btn.setOnClickListener{
            if (ev.text.isEmpty()){
                Toast.makeText(this.getApplicationContext(),"Edit Text가 비었습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                val data: String = ev.text.toString()
                val intent = Intent(this, SubActivity::class.java)
                intent.putExtra("data", data)
                startActivityForResult(intent, 100);
                ev.text.clear()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        val res: TextView = findViewById(R.id.res_main)
        if (resultCode == Activity.RESULT_OK){
            when (requestCode){
                100 ->{
                    res.text = data!!.getStringExtra("data") + "\n-send from sub"
                }
            }
        }
    }
}