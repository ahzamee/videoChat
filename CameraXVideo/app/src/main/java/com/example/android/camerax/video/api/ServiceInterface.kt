package com.example.android.camerax.video.api

import com.example.android.camerax.video.model.SignToTest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ServiceInterface {
    @Multipart
    @POST("signtotest")
    fun getSignToTest(@Part file: MultipartBody.Part): Call<SignToTest>;
}