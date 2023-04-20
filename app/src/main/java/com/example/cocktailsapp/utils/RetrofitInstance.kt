package com.example.cocktailsapp.utils

import com.example.cocktailsapp.data.api.TheCocktailDbAPI
import com.example.cocktailsapp.utils.Constants.BASE_URL
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

object RetrofitInstance {

    val api: TheCocktailDbAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheCocktailDbAPI::class.java)
    }

}