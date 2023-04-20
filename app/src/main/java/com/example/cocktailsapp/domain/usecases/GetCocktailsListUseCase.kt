package com.example.cocktailsapp.domain.usecases

import com.example.cocktailsapp.utils.RetrofitInstance

class GetCocktailsListUseCase {
    suspend operator fun invoke() = RetrofitInstance.api.getCocktails().body()?.cocktailsList
}