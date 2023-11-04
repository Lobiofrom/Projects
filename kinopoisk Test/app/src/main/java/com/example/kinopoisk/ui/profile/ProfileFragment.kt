package com.example.kinopoisk.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kinopoisk.databinding.FragmentProfileBinding
import com.example.kinopoisk.ui.home.MovieListAdapter
import com.example.kinopoisk.utils.onCollectionClick
import com.example.kinopoisk.utils.onItemClick
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by activityViewModels {
        ProfileViewModelFactory(
            requireActivity().application
        )
    }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewedAdapter = MovieListAdapter { item ->
            onItemClick(item, this)
        }
        val watchAdapter = MovieListAdapter { item ->
            onItemClick(item, this)
        }
        val collectionAdapter =
            ProfileCollectionDelegate(profileViewModel) { collectionWithMovies ->
                onCollectionClick(collectionWithMovies, this)
            }
        binding.collectionRecycler.adapter = collectionAdapter
        binding.viewedRecycler.adapter = viewedAdapter
        binding.interestingRecycler.adapter = watchAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                profileViewModel.collectionList.collect { collectionList ->
                    if (collectionList.isNotEmpty()) {
                        collectionAdapter.submitList(collectionList.subList(1, collectionList.size))

                        val viewed = collectionList.find {
                            it.collection.collectionName == "Viewed"
                        }
                        val wantToWatchCollection = collectionList.find {
                            it.collection.collectionName == "Хочу посмотреть"
                        }
                        profileViewModel.getViewedList(viewed?.movies!!)
                        profileViewModel.getWantToWatchList(wantToWatchCollection?.movies!!)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                profileViewModel.viewedList.collect {
                    viewedAdapter.submitList(it)
                    binding.viewedSize.text = it.size.toString()
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                profileViewModel.wantToWatch.collect {
                    watchAdapter.submitList(it)
                    binding.interestingSize.text = it.size.toString()
                }
            }
        }
        binding.textViewCreateNewCollectionBottom.setOnClickListener {
            showAddCollectionDialog()
        }
        binding.imageViewCloseEditCollectionBottom.setOnClickListener {
            unShowAddCollectionDialog()
        }
        binding.textInputLayoutCollectionBottom.setEndIconOnClickListener {
            val collectionName = binding.editTextCollectionBottom.text.toString()
            if (collectionName.isNotEmpty()) {
                profileViewModel.addCollection(collectionName)
                unShowAddCollectionDialog()
            }
        }
    }

    private fun showAddCollectionDialog() {
        binding.cardViewEditCollectionBottom.visibility = View.VISIBLE
        binding.scroll.visibility = View.GONE
    }

    private fun unShowAddCollectionDialog() {
        binding.cardViewEditCollectionBottom.visibility = View.GONE
        binding.scroll.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}