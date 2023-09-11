package com.example.kinopoisk.ui.seriesFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.databinding.ItemSeasonBinding
import com.example.kinopoisk.entity.Season

class SeasonCountAdapter(
    private val seasonList: List<Season>
) : RecyclerView.Adapter<SeasonCountViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonCountViewHolder {
        return SeasonCountViewHolder(
            ItemSeasonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return seasonList.size
    }

    override fun onBindViewHolder(holder: SeasonCountViewHolder, position: Int) {
        val item = seasonList[position]
        holder.binding.chip.text = (item.number + if (item.number == 0) 1 else 0).toString()
    }
}

class SeasonCountViewHolder(val binding: ItemSeasonBinding) : RecyclerView.ViewHolder(binding.root)