package com.example.myapplication.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Name (
    @SerialName("first" )
    var first: String? = null,

    @SerialName("last")
    var last: String? = null
)
