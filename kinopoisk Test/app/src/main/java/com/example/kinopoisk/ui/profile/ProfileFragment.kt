package com.example.kinopoisk.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kinopoisk.databinding.FragmentProfileBinding
import com.example.kinopoisk.ui.detail_fragment.DBViewModel
import com.example.kinopoisk.ui.detail_fragment.DBViewModelFactory

class ProfileFragment : Fragment() {

    private val dbViewModel: DBViewModel by viewModels { DBViewModelFactory(requireActivity().application) }

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

        val collectionAdapter = ProfileCollectionDelegate()
        binding.collectionRecycler.adapter = collectionAdapter
        dbViewModel.allCollectionsWithMovies.observe(viewLifecycleOwner) { list ->
            collectionAdapter.submitList(list.subList(1, list.size))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}