package com.example.kinopoisk.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.kinopoisk.databinding.FragmentSearchBinding
import com.example.kinopoisk.ui.fullmovielist.FullMovieListAdapter
import com.example.kinopoisk.ui.onItemClick.onItemClick
import com.example.kinopoisk.ui.onItemClick.onPersonSearchClick
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val dashboardViewModel: SearchViewModel by activityViewModels()
    private val adapter = FullMovieListAdapter {
        onItemClick(it, this)
    }
    private val personAdapter = SearchPersonAdapter { item, view ->
        onPersonSearchClick(item, view, this)
    }

    private val adapterDelegate = ListDelegationAdapter(
        personAdapterDelegate { item, view ->
            onPersonSearchClick(item, view, this)
        },
        movieAdapterDelegate {
            onItemClick(it, this)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerSearch.adapter = adapterDelegate

        binding.settingsButton.setOnClickListener {
            val keyword = binding.searchView.query.toString()
            dashboardViewModel.searchP(keyword)
            dashboardViewModel.search(keyword)
            dashboardViewModel.combinedFlow.onEach { data ->
                adapterDelegate.items = data
                adapterDelegate.notifyDataSetChanged()
            }.launchIn(viewLifecycleOwner.lifecycleScope)


//            dashboardViewModel.getSearch(keyword).onEach {
//                adpterDelegate.submitList(it)
//            }.launchIn(viewLifecycleOwner.lifecycleScope)

//            dashboardViewModel.getPersons(keyword).onEach {
//                adpterDelegate.
//            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}