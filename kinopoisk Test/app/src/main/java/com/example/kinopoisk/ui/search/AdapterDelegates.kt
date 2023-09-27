package com.example.kinopoisk.ui.search

import android.view.View
import android.widget.ImageView
import coil.load
import com.example.kinopoisk.databinding.ItemBinding
import com.example.kinopoisk.databinding.ItemSearchedPersonBinding
import com.example.kinopoisk.entity.BaseMediaItem
import com.example.kinopoisk.entity.Movie
import com.example.kinopoisk.entity.StaffItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun personAdapterDelegate(onClick: (StaffItem, ImageView) -> Unit) =
    adapterDelegateViewBinding<StaffItem, BaseMediaItem, ItemSearchedPersonBinding>(
        { layoutInflater, root ->
            ItemSearchedPersonBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        binding.root.setOnClickListener {
            onClick.invoke(item, binding.imageView)
        }
        bind {
            binding.imageView.load(item.posterUrl)
            binding.personName.text = item.nameRu
        }
    }

fun movieAdapterDelegate(onClick: (Movie) -> Unit) =
    adapterDelegateViewBinding<Movie, BaseMediaItem, ItemBinding>(
        { layoutInflater, root ->
            ItemBinding.inflate(layoutInflater, root, false)
        }
    ) {
        binding.root.setOnClickListener {
            onClick.invoke(item)
        }
        bind {
            if (!item.rating.isNullOrEmpty()) {
                binding.rating.visibility = View.VISIBLE
                binding.rating.text = item.rating
            } else {
                binding.rating.visibility = View.GONE
            }
            binding.movieName.text = item.nameRu ?: ""
            binding.movieGenre.text = item.genres?.joinToString(", ") {
                it.genre.toString()
            }
            binding.imageView.load(item.posterUrlPreview)
        }
    }
