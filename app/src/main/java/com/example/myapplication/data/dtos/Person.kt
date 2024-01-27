package com.example.myapplication.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    @SerialName("name")
    var name: Name? = Name(),

    @SerialName("location")
    var location: Location? = Location(),

    @SerialName("dob")
    var dob: DateOfBirth? = DateOfBirth(),

    @SerialName("picture")
    var picture: Picture? = Picture(),
)