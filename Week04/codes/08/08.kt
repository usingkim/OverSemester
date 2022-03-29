package com.example.example08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

fun isEmpty(num1: String, num2: String): Boolean{
    if (num1 == "" || num2 == "") return true
    else return false
}

class MainActivity : AppCompatActivity(){
    lateinit var edit1 : EditText
    lateinit var edit2 : EditText
    lateinit var btnAdd : Button
    lateinit var btnSub : Button
    lateinit var btnMul : Button
    lateinit var btnDiv : Button
    lateinit var btnRes : Button
    lateinit var btnRep : Button
    lateinit var textResult : TextView
    lateinit var num1 : String
    lateinit var num2 : String
    var result : Int? = null
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "0회 계산기"

        edit1 = findViewById<EditText>(R.id.Edit1)
        edit2 = findViewById<EditText>(R.id.Edit2)

        btnAdd = findViewById<Button>(R.id.BtnAdd)
        btnSub = findViewById<Button>(R.id.BtnSub)
        btnMul = findViewById<Button>(R.id.BtnMul)
        btnDiv = findViewById<Button>(R.id.BtnDiv)
        btnRes = findViewById<Button>(R.id.BtnRes)
        btnRep = findViewById<Button>(R.id.BtnRep)

        textResult = findViewById<TextView>(R.id.TextResult)

        fun change(){
            textResult.text = "계산 결과 : " + result.toString()
            count += 1
            title = count.toString() + "회 계산기"

            edit1.setText(result.toString())
            edit2.setText("")
        }

        btnAdd.setOnClickListener {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if(isEmpty(num1, num2) == false) {
                result = Integer.parseInt(num1) + Integer.parseInt(num2)
                change()
            }
        }

        btnSub.setOnClickListener {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if(isEmpty(num1, num2) == false) {
                result = Integer.parseInt(num1) - Integer.parseInt(num2)
                change()
            }
        }

        btnMul.setOnClickListener {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if(isEmpty(num1, num2) == false) {
                result = Integer.parseInt(num1) * Integer.parseInt(num2)
                change()
            }
        }

        btnDiv.setOnClickListener {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if (num2 != 0.toString() && isEmpty(num1, num2) == false) {
                result = Integer.parseInt(num1) / Integer.parseInt(num2)
                change()
            }
        }

        btnRes.setOnClickListener {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if (num2 != 0.toString() && isEmpty(num1, num2) == false) {
                result = Integer.parseInt(num1) % Integer.parseInt(num2)
                change()
            }
        }

        btnRep.setOnClickListener {
            num1 = edit1.text.toString()
            num2 = edit2.text.toString()
            if (isEmpty(num1, num2) == false) {
                edit1.setText(num2)
                edit2.setText(num1)
            }
        }
    }
}