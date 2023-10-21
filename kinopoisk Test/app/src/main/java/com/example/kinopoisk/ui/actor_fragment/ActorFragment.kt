package com.example.kinopoisk.ui.actor_fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentActorBinding
import com.example.kinopoisk.ui.detail_fragment.DBViewModel
import com.example.kinopoisk.ui.detail_fragment.DBViewModelFactory
import com.example.kinopoisk.ui.home.MovieListAdapter
import com.example.kinopoisk.ui.onItemClick.onItemClick
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ActorFragment : Fragment() {

    private var _binding: FragmentActorBinding? = null
    private val binding get() = _binding!!

    private val adapter = MovieListAdapter { item ->
        onItemClick(item, this)
    }

    private val dbViewModel: DBViewModel by activityViewModels { DBViewModelFactory(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActorBinding.inflate(inflater, container, false)

        dbViewModel.allCollections.onEach { movieCollections ->
            Log.d(
                "tag", "Коллекции: ${
                    movieCollections.joinToString(", ") {
                        it.collectionName
                        it.movieIdList.toString()
                    }
                }"
            )
        }.launchIn(viewLifecycleOwner.lifecycleScope)


        val viewModel = ViewModelProvider(this)[ActorViewModel::class.java]

        val id = arguments?.getInt("personId")

        viewModel.getActorInfo(id!!)

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.actorInfo.collect {
                    binding.actorPhoto.load(it?.posterUrl)
                    binding.actorName.text = it?.nameRu
                    binding.actorProfession.text = it?.profession
                    binding.filmCount.text = it?.films?.size?.toString() ?: ""

                    val films = it?.films

                    binding.fullList.setOnClickListener {
                        val bundle = Bundle()
                        val filmographyArrayList = films as? ArrayList<out Parcelable>
                        bundle.putParcelableArrayList("filmography", filmographyArrayList)
                        findNavController().navigate(R.id.actorFilmographyFragment, bundle)
                    }

                }
            }
        }

        binding.bestRecycler.adapter = adapter

        viewModel.filteredList.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}