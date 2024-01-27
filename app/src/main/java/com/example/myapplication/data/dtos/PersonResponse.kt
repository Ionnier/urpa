package com.example.myapplication.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonResponse (
    @SerialName("results")
    var results: ArrayList<Person> = arrayListOf()
)
