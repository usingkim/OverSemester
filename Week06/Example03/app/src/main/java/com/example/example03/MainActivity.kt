package com.example.example03

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var GALLERY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn : Button = findViewById(R.id.btn_main)
        var ev_name: EditText = findViewById(R.id.ev_name)
        var ev_age: EditText = findViewById(R.id.ev_age)
        var ev_phone: EditText = findViewById(R.id.ev_phone)
        var ev_address: EditText = findViewById(R.id.ev_address)
        var ev_etc: EditText = findViewById(R.id.ev_etc)
        var image_text: TextView = findViewById(R.id.image_text)

        btn.setOnClickListener{
            val name: String = ev_name.text.toString()
            val age: String = ev_age.text.toString()
            val phone: String = ev_phone.text.toString()
            val address: String = ev_address.text.toString()
            val etc: String = ev_etc.text.toString()
            val image: ImageView = findViewById(R.id.image)

            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("age", age)
            intent.putExtra("phone", phone)
            intent.putExtra("address", address)
            intent.putExtra("etc", etc)
            //intent.putExtra("image", image)
            startActivityForResult(intent, 2)
        }
        image_text.setOnClickListener{
            openGallery()
        }

    }

    private fun resizeBitmap(bitmap:Bitmap, width:Int, height:Int): Bitmap{
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
                1 ->{
                    var ImageData: Uri? = data?.data
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImageData)
                        val image: ImageView = findViewById(R.id.image)
                        image.setImageBitmap(bitmap)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }

            }
        }
        if (resultCode == 11){
            var ev_name: EditText = findViewById(R.id.ev_name)
            var ev_age: EditText = findViewById(R.id.ev_age)
            var ev_phone: EditText = findViewById(R.id.ev_phone)
            var ev_address: EditText = findViewById(R.id.ev_address)
            var ev_etc: EditText = findViewById(R.id.ev_etc)
            var image: ImageView = findViewById(R.id.image)

            image.setImageResource(0)
            ev_name.text.clear()
            ev_age.text.clear()
            ev_phone.text.clear()
            ev_address.text.clear()
            ev_etc.text.clear()
        }
    }


}