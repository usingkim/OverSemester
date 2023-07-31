package com.example.week9_2.retrofit

import com.example.week9_2.model.PageListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    // 10 : GET Method를 이용한 데이터 통신
    @GET("/v2/everything")
    // 12-17 : 데이터를 요청하는 쿼리문, 콜백 결과로 PageListModel 데이터 반환
    fun getList(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("page") page: Long,
        @Query("pageSize") pageSize: Int
    ): Call<PageListModel>
}

