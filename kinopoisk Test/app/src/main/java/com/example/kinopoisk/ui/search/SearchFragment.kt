package com.example.kinopoisk.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.kinopoisk.databinding.FragmentSearchBinding
import com.example.kinopoisk.ui.onItemClick.onItemClick
import com.example.kinopoisk.ui.onItemClick.onPersonSearchClick
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val dashboardViewModel: SearchViewModel by activityViewModels()

    private val personAdapter by lazy {
        PersonDelegateAdapter {
            onPersonSearchClick(it, this)
        }
    }
    private val movieAdapter by lazy {
        MovieDelegateAdapter {
            onItemClick(it, this)
        }
    }

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

        binding.recyclerSearch.adapter = movieAdapter
        binding.recyclerPerson.adapter = personAdapter

        binding.settingsButton.setOnClickListener {
            val keyword = binding.searchView.query.toString()
            dashboardViewModel.getMovies(keyword).onEach {
                movieAdapter.submitData(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            dashboardViewModel.getPersons(keyword).onEach {
                personAdapter.submitData(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}