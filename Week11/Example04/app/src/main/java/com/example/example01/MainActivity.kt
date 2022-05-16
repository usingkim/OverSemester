package com.example.example01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.example01.databinding.ActivityMainBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val db = Firebase.firestore
    var oSysMainLoop = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adocRef = db.collection("player").document("test@gmail.com")

        adocRef.addSnapshotListener(EventListener<DocumentSnapshot>{ snapshot, e ->
            if (snapshot != null && snapshot.exists()){
                binding.textView.text = snapshot.data!!["xloc"].toString()
            }
        })

        if (oSysMainLoop == 0){
            oSysMainLoop = 1
            timer(period = 1500, initialDelay = 1000){
                if(oSysMainLoop != 1){
                    cancel()
                }
                val axloc = hashMapOf("xloc" to (0..100).random())
                db.collection("player").document("test@gmail.com").set(axloc)
                    .addOnSuccessListener {
                        Toast.makeText(applicationContext, "Success!", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener{
                        Toast.makeText(applicationContext, "Failure!", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

}