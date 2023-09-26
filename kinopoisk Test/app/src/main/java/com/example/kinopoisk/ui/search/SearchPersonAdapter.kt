package com.example.kinopoisk.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kinopoisk.databinding.ItemSearchedPersonBinding
import com.example.kinopoisk.entity.StaffItem
import com.example.kinopoisk.ui.detail_fragment.StaffDiffUtilCallback

class SearchPersonAdapter(
    private val onClick: (StaffItem, ImageView) -> Unit
) :
    PagingDataAdapter<StaffItem, SearchPersonViewHolder>(StaffDiffUtilCallback()) {
    override fun onBindViewHolder(holder: SearchPersonViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            imageView.load(item?.posterUrl)
            personName.text = item?.nameRu
            root.setOnClickListener {
                onClick.invoke(item!!, imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPersonViewHolder {
        return SearchPersonViewHolder(
            ItemSearchedPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

class SearchPersonViewHolder(val binding: ItemSearchedPersonBinding) :
    RecyclerView.ViewHolder(binding.root)