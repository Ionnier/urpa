package com.example.myapplication.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Picture (
    @SerialName("thumbnail")
    var link : String? = null
)
