package com.ucne.parcial2.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.repository.TicketsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideTickets(moshi: Moshi): TicketsApi {
        return Retrofit.Builder()
            .baseUrl("https://teprestoapi.azurewebsites.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TicketsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTicketsRepository(ticketsApi: TicketsApi): TicketsRepository {
        return TicketsRepository(ticketsApi)
    }
}