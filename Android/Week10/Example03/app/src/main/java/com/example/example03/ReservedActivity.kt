package com.example.example03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reserved.*
import kotlinx.android.synthetic.main.activity_reserved.posterImageView
import java.io.Serializable
import java.util.ArrayList

class ReservedActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserved)

        processIntent(intent)
        btn_close.setOnClickListener { finish() }
    }

    fun processIntent(intent: Intent?){
        val movies = intent?.getSerializableExtra("movies") as ArrayList<ReservedMovie>?
        val movie = movies?.get(0)
        println(movie.toString() + "\n")
        if (movie != null){
            posterImageView.setImageURI(Uri.parse(movie.poster_image))
            input11.setText(movie.name)
            input21.setText(movie.reserved_time)
            input31.setText(movie.director)
            input41.setText(movie.synopsis)
        }
    }

}

data class ReservedMovie(
    val _id:Int?,
    val name:String?,
    val poster_image:String?,
    val director: String?,
    val synopsis: String?,
    val reserved_time: String?
): Serializable