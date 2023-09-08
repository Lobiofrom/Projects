package com.example.testhotels.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.testhotels.R
import com.example.testhotels.data.State
import com.example.testhotels.databinding.FragmentRoomBinding
import com.example.testhotels.ui.adapters.VerticalAdapter
import com.example.testhotels.ui.viewmodel.MyViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyViewModel by viewModels()

    private val verticalAdapter = VerticalAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)

        binding.roomRecycler.adapter = verticalAdapter

        val hotelName = arguments?.getString("hotelName")

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = hotelName
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.img_4)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getRooms()

        viewModel.rooms.onEach {
            verticalAdapter.setRooms(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect {
                    when (it) {
                        State.Error -> binding.progressCircular.visibility = View.GONE
                        State.Loading -> binding.progressCircular.visibility = View.VISIBLE
                        State.Success -> binding.progressCircular.visibility = View.GONE
                    }
                }
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}