package com.example.android.camerax.video.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.camerax.video.api.ApiInstance.Companion.getApiInstance
import com.example.android.camerax.video.api.ServiceInterface
import com.example.android.camerax.video.model.SignToTest
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MainActivityViewModel: ViewModel() {

    var liveDataList: MutableLiveData<SignToTest> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<SignToTest>{
        return liveDataList
    }

    fun makeApiCall(baseUrl: String, file: File){
        val apiInstance = getApiInstance(baseUrl)
        val apiService = apiInstance.create(ServiceInterface::class.java)
        val requestBody = RequestBody.create(MediaType.parse("video/*"), file)

        val filePart = MultipartBody.Part.createFormData(
            "file_video",file.name,requestBody
        )
        val call = apiService.getSignToTest(filePart)

        call.enqueue(object : Callback<SignToTest>{

            override fun onResponse(
                call: Call<SignToTest>,
                response: Response<SignToTest>
            ) {
                Log.d("aaaaaaaaasu", response.body().toString())
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<SignToTest>, t: Throwable) {
                Log.d("aaaaaaaaafail", t.message.toString())
                liveDataList.postValue(null)
            }

        })
    }
}