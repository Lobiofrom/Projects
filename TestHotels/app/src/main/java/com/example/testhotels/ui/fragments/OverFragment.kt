package com.example.testhotels.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testhotels.R
import com.example.testhotels.databinding.FragmentOverBinding

class OverFragment : Fragment() {

    private var _binding: FragmentOverBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOverBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.hotelFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.hotelFragment)
            }
        })

        val number = (100000..999999).random()

        binding.orderNumber.text = "Подтверждение заказа №$number может занять некоторое время (от 1 часа до суток). Как только мы получим ответ от туроператора, вам на почту придет уведомление.\n"

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}