package com.example.myapplication.data.source

import com.example.myapplication.data.dtos.Person
import com.example.myapplication.data.dtos.PersonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PersonRepository(
    private val httpClient: HttpClient
) {
    companion object {
        private const val SEED = "abc"
    }

    suspend fun getAll(page: Int, results: Int): List<Person>? {
        return try {
            val response: PersonResponse =
                httpClient.get {
                    parameter("page", page)
                    parameter("results", results)
                    parameter("seed", SEED)
                }.body()
            response.results
        } catch (e: Exception) {
            null
        }
    }

}