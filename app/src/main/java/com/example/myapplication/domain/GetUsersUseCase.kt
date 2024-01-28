package com.example.myapplication.domain

import com.example.myapplication.data.source.PersonRepository
import com.example.myapplication.models.User
import java.text.SimpleDateFormat
import java.util.Locale

class GetUsersUseCase(
    private val repository: PersonRepository
) {

    suspend fun execute(page: Int, results: Int): List<User>? {
        val personList = repository.getAll(page, results) ?: return null
        return personList.map {
            User(
                fullName = it.getFullName(),
                age = it.getAge(),
                countryCode = it.getCountryCode(),
                time = it.getRandomTime(),
                avatarURL = it.getAvatarURL()
            )
        }
    }

    private fun com.example.myapplication.data.dtos.Person.getFullName(): String {
        val first = this.name?.first ?: return ""
        val last = this.name?.last ?: return ""
        return "$first $last"
    }

    private fun com.example.myapplication.data.dtos.Person.getAge(): Int {
        return this.dob?.age ?: 0
    }

    private fun com.example.myapplication.data.dtos.Person.getCountryCode(): String {
        val country = this.location?.country ?: ""
        return Locale.getISOCountries().find { Locale("", it).displayCountry == country } ?: ""
    }

    private fun com.example.myapplication.data.dtos.Person.getRandomTime(): String {
        val dateString = this.dob?.date ?: ""
        val date =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(dateString) ?: return ""
        return SimpleDateFormat("mm:ss", Locale.getDefault()).format(date)
    }

    private fun com.example.myapplication.data.dtos.Person.getAvatarURL(): String {
        return this.picture?.link ?: ""
    }
}