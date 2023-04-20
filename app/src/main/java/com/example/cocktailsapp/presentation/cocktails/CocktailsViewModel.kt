package com.example.cocktailsapp.presentation.cocktails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailsapp.data.models.Cocktail
import com.example.cocktailsapp.domain.usecases.GetCocktailsListUseCase
import kotlinx.coroutines.launch

class CocktailsViewModel: ViewModel() {

    private val getCocktailsListUseCase = GetCocktailsListUseCase()

    suspend fun getAllCocktails(): List<Cocktail>? = getCocktailsListUseCase.invoke()

}