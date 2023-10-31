package com.example.kinopoisk.ui.detail_fragment

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.example.kinopoisk.databinding.ItemBottomSheetRecyclerBinding
import com.example.kinopoisk.entity.dBCollection.CollectionWithMovies
import ru.sr.adapter.ListDelegateAdapter
import ru.sr.adapter.adapterDelegate

class BottomSheetAdapter(
//    collectionList: List<CollectionWithMovies>,
//    movieId: Int
) :
    ListDelegateAdapter<CollectionWithMovies>(CollectionWithMoviesDiffUtil()) {
    init {
        addDelegate(
            collectionAdapterDelegate(
                //  collectionList, movieId
            )
        )
    }
}

fun collectionAdapterDelegate(
//    collectionList: List<CollectionWithMovies>,
//    movieId: Int
) =
    adapterDelegate<CollectionWithMovies, CollectionWithMovies, ItemBottomSheetRecyclerBinding>(
        { parent ->
            ItemBottomSheetRecyclerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        }
    ) {
        bind {
            binding.textViewNameCollectionCheckBox.text = item.collection.collectionName
            binding.textViewCounterCollectionCheckBox.text = item.movies.size.toString()
//            if (collectionList.any { collection ->
//                    collection.movies.any { movie ->
//                        movie.movieId == movieId
//                    }
//                }) {
//
//            }
        }
    }

class CollectionWithMoviesDiffUtil : DiffUtil.ItemCallback<CollectionWithMovies>() {
    override fun areItemsTheSame(
        oldItem: CollectionWithMovies,
        newItem: CollectionWithMovies
    ): Boolean = oldItem.collection.collectionId == newItem.collection.collectionId

    override fun areContentsTheSame(
        oldItem: CollectionWithMovies,
        newItem: CollectionWithMovies
    ): Boolean = oldItem == newItem
}