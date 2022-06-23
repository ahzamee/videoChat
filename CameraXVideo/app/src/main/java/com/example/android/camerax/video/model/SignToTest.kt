package com.example.android.camerax.video.model

import com.google.gson.annotations.SerializedName

data class SignToTest(
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: List<String>?,
)