package com.example.cocktailsapp.domain.usecases

import com.example.cocktailsapp.utils.RetrofitInstance

class GetCocktailByIdUseCase {
    suspend operator fun invoke(id: String) = RetrofitInstance.api.getCocktailById(id).body()?.cocktailsList?.first()
}