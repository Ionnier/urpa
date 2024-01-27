package com.example.myapplication.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DateOfBirth (
    @SerialName("date" ) var date : String? = null,
    @SerialName("age"  ) var age  : Int?    = null
)
