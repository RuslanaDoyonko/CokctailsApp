package com.example.cocktailsapp.presentation.cocktails

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailsapp.data.models.Cocktail
import com.example.cocktailsapp.databinding.FragmentCocktailsBinding
import com.example.cocktailsapp.presentation.ui.CocktailRVAdapter
import retrofit2.HttpException
import java.io.IOException

class CocktailsFragment : Fragment() {

    private lateinit var binding: FragmentCocktailsBinding

    private val viewModel: CocktailsViewModel by viewModels()

    private lateinit var cocktailsRVAdapter: CocktailRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCocktailsBinding.inflate(layoutInflater)

        cocktailsRVAdapter = CocktailRVAdapter()
        binding.rvCocktails.layoutManager = LinearLayoutManager(context)
        binding.rvCocktails.adapter = cocktailsRVAdapter

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            val list = try {
                viewModel.getAllCocktails()
            } catch (e: IOException) {
                Log.e("NETWORKING", "IOException")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e("NETWORKING", "HttpException")
                return@launchWhenCreated
            }

            if (!list.isNullOrEmpty())
                cocktailsRVAdapter.cocktailsList = list as MutableList<Cocktail>

            cocktailsRVAdapter.notifyDataSetChanged()
        }

        cocktailsRVAdapter.setOnClickListener(object : CocktailRVAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
                view.findNavController().navigate(
                    CocktailsFragmentDirections.actionCocktailsFragmentToDetailedFragment().also {
                        it.cocktailId = cocktailsRVAdapter.cocktailsList[position].id.toString()
                    }
                )
            }
        })
    }

}