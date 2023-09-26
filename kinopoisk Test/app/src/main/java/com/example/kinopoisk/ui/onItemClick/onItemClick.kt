package com.example.kinopoisk.ui.onItemClick

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.kinopoisk.R
import com.example.kinopoisk.entity.Item
import com.example.kinopoisk.entity.Movie
import com.example.kinopoisk.entity.StaffItem

fun onItemClick(item: Movie, fragment: Fragment) {
    val bundle = Bundle()
    val filmId = item.filmId
    val kinopoiskId = item.kinopoiskId

    bundle.putInt("filmId", filmId)
    bundle.putInt("kinopoiskId", kinopoiskId)

    fragment.findNavController().navigate(R.id.detailFragment, bundle)
}

fun onPictureClick(item: Item, imageView: ImageView, fragment: Fragment) {
    val bundle = Bundle()
    val pictureUrl = item.imageUrl
    bundle.putString("pictureUrl", pictureUrl)

    val extras = FragmentNavigatorExtras(imageView to "picture")

    fragment.findNavController().navigate(R.id.bigPictureFragment, bundle, null, extras)
}

fun onPersonClick(item: StaffItem, imageView: ImageView, fragment: Fragment) {
    val bundle = Bundle()
    val personId = item.staffId
    bundle.putInt("personId", personId)
    fragment.findNavController().navigate(R.id.actorFragment, bundle)
}

fun onPersonSearchClick(item: StaffItem, imageView: ImageView, fragment: Fragment) {
    val bundle = Bundle()
    val personId = item.kinopoiskId
    bundle.putInt("personId", personId)
    fragment.findNavController().navigate(R.id.actorFragment, bundle)
}