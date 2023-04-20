package com.example.cocktailsapp.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailsapp.data.models.Cocktail
import com.example.cocktailsapp.databinding.ItemCocktailBinding

class CocktailRVAdapter : RecyclerView.Adapter<CocktailRVAdapter.CocktailRVViewHolder>() {

    var cocktailsList = mutableListOf<Cocktail>()
    private lateinit var clickListener: OnItemClickListener

    class CocktailRVViewHolder(
        private val binding: ItemCocktailBinding,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }

        fun bind(item: Cocktail) = with(binding) {
            itTitle.text = item.name
            Glide
                .with(binding.root)
                .load(item.pictureUrl)
                .into(binding.itIcon)
        }

    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailRVViewHolder {
        return CocktailRVViewHolder(
            ItemCocktailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun getItemCount(): Int {
        return cocktailsList.size
    }

    override fun onBindViewHolder(holder: CocktailRVViewHolder, position: Int) {
        holder.bind(cocktailsList[position])
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

}