package com.example.testhotels.ui.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.testhotels.R
import com.example.testhotels.data.State
import com.example.testhotels.databinding.FragmentBookBinding
import com.example.testhotels.entity.passenger.Passenger
import com.example.testhotels.ui.adapters.PassengerAdapter
import com.example.testhotels.ui.viewmodel.MyViewModel
import kotlinx.coroutines.launch

class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyViewModel by viewModels()

    private var isEmailValid = false
    private var isPhoneValid = false

    private var touristCount = 0

    private val passengerList = mutableListOf(addPassenger())

    private val adapter = PassengerAdapter(passengerList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.img_4)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getBooking()

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.booking.collect {
                    binding.textPrevoshodno.text = "${it?.horating ?: ""} ${it?.rating_name ?: ""}"
                    binding.hotelName.text = it?.hotel_name
                    binding.hotelAddress.text = it?.hotel_adress
                    binding.viletIz.text = it?.departure
                    binding.country.text = it?.arrival_country
                    binding.date.text = "${it?.tour_date_start ?: ""} - ${it?.tour_date_stop ?: ""}"
                    binding.nightCount.text = it?.number_of_nights?.toString() ?: ""
                    binding.palceName.text = it?.hotel_name
                    binding.roomType.text = it?.room
                    binding.food.text = it?.nutrition
                    binding.tourPrice.text = it?.tour_price.toString()
                    binding.tax1.text = it?.fuel_charge.toString()
                    binding.tax2.text = it?.service_charge.toString()

                    val tourPrice = it?.tour_price ?: 0
                    val fuelCharge = it?.fuel_charge ?: 0
                    val serviceCharge = it?.service_charge ?: 0

                    val sum = tourPrice + fuelCharge + serviceCharge
                    binding.total.text = sum.toString()
                    binding.button.text = "оплатить $sum"
                }
            }
        }

        binding.testRecycler.adapter = adapter

        binding.addTouristButton.setOnClickListener {
            adapter.addItem(addPassenger())
        }


        binding.email.doOnTextChanged { text, _, _, _ ->
            isEmailValid = validateEmail(text)
            if (validateEmail(text)) {
                binding.TextInputLayout2.isErrorEnabled = false
            } else {
                binding.TextInputLayout2.error = "некорректный адрес"
                binding.TextInputLayout2.isErrorEnabled = true
            }
        }
        binding.telNumber.doOnTextChanged { text, _, _, _ ->
            isPhoneValid = !text.isNullOrEmpty() && text.length == 10
            if (text.isNullOrEmpty() || text.length != 10) {
                binding.TextInputLayout1.error = "некорректный номер"
                binding.TextInputLayout1.isErrorEnabled = true
            } else {
                binding.TextInputLayout1.isErrorEnabled = false
            }
        }

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

        binding.button.setOnClickListener {
            val isPassengersValid = adapter.isAllValid
            val isDataValidForNewPassenger = adapter.passengerStatesList
                .filterIndexed { index, _ ->
                    index >= adapter.passengerList.size - 1
                }
                .all { it.isAllValid() }

            if (isPhoneValid && isEmailValid && isPassengersValid && isDataValidForNewPassenger) {
                findNavController().navigate(R.id.overFragment)
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun addPassenger(): Passenger {
        touristCount++
        return Passenger(
            text = "Турист",
            tourist_count = touristCount,
            name = null,
            surname = null,
            birthdate = null,
            nationality = null,
            passportN = null,
            passportTime = null
        )
    }

    private fun validateEmail(email: CharSequence?): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}