package com.example.myapplication.domain

import com.example.myapplication.data.source.PersonRepository
import com.example.myapplication.models.Person
import java.util.Locale

class GetUsersUseCase(
    private val repository: PersonRepository
) {

    suspend fun execute(page: Int, results: Int): List<Person>? {
        val personList = repository.getAll(page, results) ?: return null
        return personList.map {
            Person(
                fullName = it.getFullName(),
                age = it.getAge(),
                countryCode = it.getCountryCode(),
                time = it.getRandomTime()
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
        val date = this.dob?.date ?: ""
        return date.split("T")[1].replace("Z", "")
    }
}