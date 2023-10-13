package com.example.kinopoisk.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentSearchSettingsBinding

class SearchSettingsFragment : Fragment() {

    private var _binding: FragmentSearchSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.spinnerCountry.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.Countries_key)
        )

        binding.spinnerGenre.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.Genre_key)
        )

        binding.typeLeftButton.setOnClickListener {
            val typeList = resources.getStringArray(R.array.type_movie)
            viewModel.type = typeList[0]
        }

        binding.typeCenterButton.setOnClickListener {
            val typeList = resources.getStringArray(R.array.type_movie)
            viewModel.type = typeList[1]
        }

        binding.typeRightButton.setOnClickListener {
            val typeList = resources.getStringArray(R.array.type_movie)
            viewModel.type = typeList[2]
        }

        binding.sortLeftButton.setOnClickListener {
            val typeList = resources.getStringArray(R.array.order)
            viewModel.order = typeList[0]
        }

        binding.sortCenterButton.setOnClickListener {
            val typeList = resources.getStringArray(R.array.order)
            viewModel.order = typeList[1]
        }

        binding.sortRightButton.setOnClickListener {
            val typeList = resources.getStringArray(R.array.order)
            viewModel.order = typeList[2]
        }

        binding.spinnerCountry.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCountry =
                        resources.getIntArray(R.array.Countries_int_value)[position]

                    if (selectedCountry == 0) {
                        viewModel.countries = null
                    } else {
                        viewModel.countries = selectedCountry
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        binding.spinnerGenre.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedGenre =
                        resources.getIntArray(R.array.Genre_int_value)[position]

                    if (selectedGenre == 0) {
                        viewModel.genres = null
                    } else {
                        viewModel.genres = selectedGenre
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        binding.yearsSeekBar.addOnChangeListener { slider, _, _ ->
            val minValue = slider.values.first().toInt()
            val maxValue = slider.values.last().toInt()

            if (minValue == 1950 && maxValue == 2023) binding.textViewYearCurrent.text = "Любой"
            else binding.textViewYearCurrent.text = buildString {
                append("С ")
                append(minValue)
                append(" - до ")
                append(maxValue)
                append(" гг")
            }
            viewModel.yearFrom = minValue
            viewModel.yearTo = maxValue
        }

        binding.ratingSeekBar.addOnChangeListener { slider, _, _ ->
            val minValue = slider.values.first().toInt()
            val maxValue = slider.values.last().toInt()

            if (minValue == 0 && maxValue == 10) binding.textViewYearCurrent.text = "Любой"
            else binding.textViewRatingCurrent.text = buildString {
                append(minValue)
                append(" - ")
                append(maxValue)
            }
            viewModel.ratingFrom = minValue
            viewModel.ratingTo = maxValue
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}