package com.example.cocktailsapp.presentation.detailed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.cocktailsapp.data.models.Cocktail
import com.example.cocktailsapp.domain.usecases.GetCocktailByIdUseCase

class DetailedViewModel(
    private val state: SavedStateHandle
) : ViewModel() {

    private val getCocktailByIdUseCase = GetCocktailByIdUseCase()

    suspend fun getCocktailById(): Cocktail? = getCocktailByIdUseCase.invoke(state["cocktailId"]!!)

}