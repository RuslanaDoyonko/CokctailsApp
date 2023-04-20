package com.example.cocktailsapp.presentation.detailed

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.cocktailsapp.databinding.FragmentDetailedBinding
import retrofit2.HttpException
import java.io.IOException

class DetailedFragment : Fragment() {

    private lateinit var binding: FragmentDetailedBinding

    private val viewModel: DetailedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailedBinding.inflate(layoutInflater)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            val cocktail = try {
                viewModel.getCocktailById()
            } catch (e: IOException) {
                Log.e("NETWORKING", "IOException")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e("NETWORKING", "HttpException")
                return@launchWhenCreated
            }

            if (cocktail != null) {
                binding.tvTitle.text = "${cocktail.name}\nId: ${cocktail.id}"
                binding.tvInstructions.text = cocktail.instructions
                Glide
                    .with(binding.root)
                    .load(cocktail.pictureUrl)
                    .into(binding.ivIcon)
            }
        }
    }
}