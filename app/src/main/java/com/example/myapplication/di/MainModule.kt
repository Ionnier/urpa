package com.example.myapplication.di

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.myapplication.data.source.PersonRepository
import com.example.myapplication.domain.GetUsersUseCase
import com.example.myapplication.domain.UserPagination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient(CIO) {
        install(Logging) {
            logger = object: Logger {
                override fun log(message: String) {
                    Log.v("HTTP", message)
                }
            }
            level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url("https://randomuser.me/api/")
        }
    }

    @Provides
    @Singleton
    fun providePersonRepository(httpClient: HttpClient) = PersonRepository(httpClient)

    @Provides
    @Singleton
    fun provideUseCase(personRepository: PersonRepository) = GetUsersUseCase(personRepository)

    @Provides
    @Singleton
    fun provideUserPagination(useCase: GetUsersUseCase, pagingConfig: PagingConfig) =
        Pager(pagingConfig) {
            UserPagination(useCase)
        }

    @Provides
    @Singleton
    fun providePagingConfig() = PagingConfig(pageSize = 20, initialLoadSize = 20, prefetchDistance = 3)

}