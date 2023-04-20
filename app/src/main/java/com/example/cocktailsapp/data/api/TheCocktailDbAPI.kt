package com.example.cocktailsapp.data.api

import com.example.cocktailsapp.data.models.CocktailHolder
import com.example.cocktailsapp.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCocktailDbAPI {

    @GET("api/json/v1/$API_KEY/filter.php?c=Cocktail")
    suspend fun getCocktails(): Response<CocktailHolder>

    @GET("api/json/v1/$API_KEY/lookup.php")
    suspend fun getCocktailById(@Query("i") id: String): Response<CocktailHolder>

}