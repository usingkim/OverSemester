package com.example.week9_2

import android.app.Application
import com.example.week9_2.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    companion object {
        // 12 - 15 : 쿼리 전송을 위한 요구 데이터
        val QUERY = "travel"
        val API_KEY = "079dac74a5f94ebdb990ecf61c8854b7"
        val BASE_URL = "https://newsapi.org"
        val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"

        var networkService: NetworkService
        // 19 - 23 : Retrofit Builder 선언
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        // 24 : Retrofit Initialization
        init {
            networkService = retrofit.create(NetworkService::class.java)
        }
    }

}