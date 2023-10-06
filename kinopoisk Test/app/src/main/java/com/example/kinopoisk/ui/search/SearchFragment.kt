package com.example.kinopoisk.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val keyword = newText.orEmpty()

                dashboardViewModel.getMovies(keyword).onEach {
                    movieAdapter.submitData(it)
                    movieAdapter.addLoadStateListener { loadState ->
                        if (loadState.append.endOfPaginationReached) {
                            if (movieAdapter.itemCount < 1) {
                                binding.errortextmovie.visibility = View.VISIBLE
                            }
                        }
                        if (movieAdapter.itemCount > 1) {
                            binding.errortextmovie.visibility = View.GONE
                        }
                    }
                }.launchIn(viewLifecycleOwner.lifecycleScope)

                dashboardViewModel.getPersons(keyword).onEach {
                    personAdapter.submitData(it)
                    personAdapter.addLoadStateListener { loadState ->
                        if (loadState.append.endOfPaginationReached) {
                            if (personAdapter.itemCount < 1) {
                                binding.errortextperson.visibility = View.VISIBLE
                            }
                        }
                        if (personAdapter.itemCount > 1) {
                            binding.errortextperson.visibility = View.GONE
                        }
                    }
                }.launchIn(viewLifecycleOwner.lifecycleScope)

                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}