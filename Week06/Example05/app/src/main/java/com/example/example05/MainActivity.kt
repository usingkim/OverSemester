package com.example.example05

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap


class MainActivity : AppCompatActivity() {
    lateinit var main_image: ImageView
    lateinit var et_k_name: EditText
    lateinit var et_name: EditText
    lateinit var et_phone: EditText
    lateinit var et_email: EditText
    lateinit var et_address: EditText
    lateinit var btn_submit: Button
    var et_core: String? = null
    var et_mbti: String? = null
    var et_student: String? = null
    var et_grow: String? = null
    var GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_image = findViewById(R.id.main_image)
        et_k_name = findViewById(R.id.et_k_name)
        et_name = findViewById(R.id.et_name)
        et_phone = findViewById(R.id.et_phone)
        et_email = findViewById(R.id.et_email)
        et_address = findViewById(R.id.et_address)
        btn_submit = findViewById(R.id.btn_submit)


        btn_submit.setOnClickListener {
            val k_name : String = et_k_name.text.toString()
            val name: String = et_name.text.toString()
            val phone: String = et_phone.text.toString()
            val email: String = et_email.text.toString()
            val address: String = et_address.text.toString()
            val image: Bitmap = resizeBitmap(main_image.drawToBitmap())

            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("k_name", k_name)
            intent.putExtra("name", name)
            intent.putExtra("phone", phone)
            intent.putExtra("email", email)
            intent.putExtra("address", address)
            intent.putExtra("image", image)

            intent.putExtra("et_core", et_core)
            intent.putExtra("et_mbti", et_mbti)
            intent.putExtra("et_student", et_student)
            intent.putExtra("et_grow", et_grow)

            startActivityForResult(intent, 2)
        }

        main_image.setOnClickListener{
            openGallery()
        }

    }

    private fun resizeBitmap(bitmap: Bitmap): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, 300, 300, false)
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when (requestCode){
                GALLERY ->{
                    var ImageData: Uri? = data?.data
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImageData)
                        val image: ImageView = findViewById(R.id.main_image)
                        image.setImageBitmap(bitmap)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
                12 ->{
                    val intent = Intent(this, SubActivity::class.java)
                    et_core = intent.getStringExtra("et_core")
                    et_student = intent.getStringExtra("et_student")
                    et_mbti = intent.getStringExtra("et_mbti")
                    et_grow = intent.getStringExtra("et_grow")
                }

            }
        }
    }
}