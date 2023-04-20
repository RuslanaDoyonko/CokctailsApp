package com.example.cocktailsapp.data.models

import com.google.gson.annotations.SerializedName

data class Cocktail(
    @SerializedName("idDrink") val id: Int,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkThumb") val pictureUrl: String,
    @SerializedName("strInstructions") val instructions: String
)

data class CocktailHolder(
    @SerializedName("drinks") val cocktailsList: List<Cocktail>
)