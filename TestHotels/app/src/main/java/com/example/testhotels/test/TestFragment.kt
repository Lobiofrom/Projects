package com.example.testhotels.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testhotels.databinding.FragmentTestBinding

class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!

    private val list = mutableListOf(
        Item(
            text = "First passenger",
            name = null,
            surname = null,
            birthdate = null,
            nationality = null,
            passportN = null,
            passportTime = null
        ),
        Item(
            text = "Second passenger",
            name = null,
            surname = null,
            birthdate = null,
            nationality = null,
            passportN = null,
            passportTime = null
        ),
        Item(
            text = "Third passenger",
            name = null,
            surname = null,
            birthdate = null,
            nationality = null,
            passportN = null,
            passportTime = null
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTestBinding.inflate(inflater, container, false)

        val adapter = MyAdapter(list)
        binding.testRecycler.adapter = adapter

        val four = Item(
            text = "Fourth passenger",
            name = null,
            surname = null,
            birthdate = null,
            nationality = null,
            passportN = null,
            passportTime = null
        )

        binding.button.setOnClickListener {
            adapter.addItem(four)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}