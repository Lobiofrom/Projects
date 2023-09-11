package com.example.kinopoisk.ui.seriesFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kinopoisk.databinding.FragmentSeriesBinding
import com.example.kinopoisk.entity.Season

class SeriesFragment : Fragment() {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)

        val seasonList = when {
            Build.VERSION.SDK_INT >= 33 -> arguments?.getParcelableArrayList(
                "seasonList",
                Season::class.java
            )

            else -> @Suppress("DEPRECATION") arguments?.getParcelableArrayList("seasonList")
        }?.toList()

        val nameRu = arguments?.getString("nameRu")
        val nameEn = arguments?.getString("nameEn")
        val nameOriginal = arguments?.getString("nameOriginal")

        binding.title.text = if (!nameRu.isNullOrEmpty()) {
            nameRu
        } else if (!nameEn.isNullOrEmpty()) {
            nameEn
        } else {
            nameOriginal
        }

        val adapter = SeasonCountAdapter(seasonList!!)

        val adapterEpisode = EpisodesAdapter(seasonList.get(0).episodes!!)

        binding.seasonsCountRecycler.adapter = adapter

        binding.episodesRecycler.adapter = adapterEpisode

        val episodeCount = seasonList.sumOf {
            it.episodes!!.size
        }

        binding.episodesCount.text = "Сезонов: ${seasonList.size}, Серий: $episodeCount"


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}