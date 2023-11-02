package com.example.kinopoisk.ui.profile

import android.view.LayoutInflater
import android.view.View
import com.example.kinopoisk.databinding.ItemCollectionBinding
import com.example.kinopoisk.entity.dBCollection.CollectionWithMovies
import com.example.kinopoisk.ui.detail_fragment.CollectionWithMoviesDiffUtil
import ru.sr.adapter.ListDelegateAdapter
import ru.sr.adapter.adapterDelegate

class ProfileCollectionDelegate : ListDelegateAdapter<CollectionWithMovies>(
    CollectionWithMoviesDiffUtil()
) {
    init {
        addDelegate(collectionDelegateInProfile())
    }
}

fun collectionDelegateInProfile() =
    adapterDelegate<CollectionWithMovies, CollectionWithMovies, ItemCollectionBinding>(
        { parent ->
            ItemCollectionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        }
    ) {
        bind {
            binding.collectionName.text = item.collection.collectionName
            binding.collectionSize.text = item.movies.size.toString()

            binding.imageViewCloseEditCollectionBottom.visibility =
                when (item.collection.collectionName) {
                    "Любимые" -> {
                        View.GONE
                    }

                    "Хочу посмотреть" -> {
                        View.GONE
                    }

                    else -> {
                        View.VISIBLE
                    }
                }
        }
    }