package com.example.testhotels.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.testhotels.R
import com.example.testhotels.data.State
import com.example.testhotels.databinding.FragmentHotelBinding
import com.example.testhotels.ui.adapters.FeaturesAdapter
import com.example.testhotels.ui.adapters.ViewPagerAdapter
import com.example.testhotels.ui.viewmodel.MyViewModel
import kotlinx.coroutines.launch

class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelBinding.inflate(inflater, container, false)

        viewModel.getHotel()

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.hotel.collect { hotel ->
                    if (hotel != null) {
                        binding.viewpager2.adapter = ViewPagerAdapter(
                            hotel.image_urls
                        )
                        binding.indicator.setViewPager(binding.viewpager2)
                        binding.recyclerPeculiarities.adapter =
                            FeaturesAdapter(hotel.about_the_hotel.peculiarities)
                    }
                    binding.hotelName.text = hotel?.name
                    binding.hotelAddress.text = hotel?.adress
                    binding.price.text = "от ${hotel?.minimal_price?.toString() ?: ""} P"
                    binding.zaTyp.text = hotel?.price_for_it
                    binding.description.text = hotel?.about_the_hotel?.description
                    binding.textPrevoshodno.text = "${hotel?.rating ?: ""} ${hotel?.rating_name ?: ""}"

                    binding.button.setOnClickListener {
                        val bundle = Bundle()
                        val hotelName = hotel?.name
                        bundle.putString("hotelName", hotelName)
                        findNavController().navigate(R.id.roomFragment, bundle)
                    }
                }
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

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}