package com.example.kinopoisk.ui.detail_fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import coil.load
import com.example.kinopoisk.R
import com.example.kinopoisk.data.State
import com.example.kinopoisk.databinding.FragmentDetailBinding
import com.example.kinopoisk.ui.gallary_fragments.PicturesAdapter
import com.example.kinopoisk.ui.gallary_fragments.PicturesViewModel
import com.example.kinopoisk.ui.gallary_fragments.PicturesViewModelFactory
import com.example.kinopoisk.ui.home.HomeFragment
import com.example.kinopoisk.ui.home.MovieListAdapter
import com.example.kinopoisk.ui.onItemClick.onItemClick
import com.example.kinopoisk.ui.onItemClick.onPersonClick
import com.example.kinopoisk.ui.onItemClick.onPictureClick
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var bottomNavBarVisibilityListener: HomeFragment.BottomNavBarVisibilityListener? = null

    private val staffAdapter = StaffAdapter { item, view ->
        onPersonClick(item, view, this)
    }
    private val actorAdapter = ActorAdapter { item, view ->
        onPersonClick(item, view, this)
    }

    private val picturesAdapter = PicturesAdapter { picture, imageView ->
        onPictureClick(picture, imageView, this)
    }
    private val similarsAdapter = MovieListAdapter { movie ->
        onItemClick(movie, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val movieAndActorsViewModel =
            ViewModelProvider(this)[MovieActorsSimilarsViewModel::class.java]

        bottomNavBarVisibilityListener = activity as? HomeFragment.BottomNavBarVisibilityListener

        bottomNavBarVisibilityListener?.setBottomNavBarVisibility(true)

        binding.galaryRecycler.adapter = picturesAdapter
        binding.actorsRecycler.adapter = actorAdapter
        binding.staffRecycler.adapter = staffAdapter
        binding.similarRecycler.adapter = similarsAdapter

        val filmId = arguments?.getInt("filmId")
        val kinopoiskId = arguments?.getInt("kinopoiskId")

        binding.galaryAll.setOnClickListener {
            val bundle = Bundle()
            if (kinopoiskId != 0) {
                bundle.putInt("id", kinopoiskId!!)
            } else {
                bundle.putInt("id", filmId!!)
            }
            findNavController().navigate(R.id.gallaryFragment, bundle)
        }

        if (kinopoiskId != 0) {
            val picturesViewModel =
                ViewModelProvider(
                    this,
                    PicturesViewModelFactory(kinopoiskId!!)
                )[PicturesViewModel::class.java]

            picturesViewModel.get20("STILL")

            picturesViewModel.pictures20.onEach {
                picturesAdapter.submitData(PagingData.from(it))
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            movieAndActorsViewModel.getAllDetails(kinopoiskId)
        } else {
            val picturesViewModel =
                ViewModelProvider(
                    this,
                    PicturesViewModelFactory(filmId!!)
                )[PicturesViewModel::class.java]

            picturesViewModel.get20("STILL")

            picturesViewModel.pictures20.onEach {
                picturesAdapter.submitData(PagingData.from(it))
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            movieAndActorsViewModel.getAllDetails(filmId)
        }

        movieAndActorsViewModel.similars.onEach {
            similarsAdapter.submitList(it)
            binding.allSimilar.text = it.size.toString()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        movieAndActorsViewModel.staffList.onEach {
            actorAdapter.run {
                submitList(it.filter {
                    it.professionKey == "ACTOR"
                })
            }
            binding.allActors.text = it.filter {
                it.professionKey == "ACTOR"
            }.size.toString()

            staffAdapter.run {
                submitList(it.filter {
                    it.professionKey != "ACTOR"
                })
            }
            binding.allStaff.text = it.filter {
                it.professionKey != "ACTOR"
            }.size.toString()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        var nameOriginal: String? = null
        var nameRu: String? = null
        var nameEn: String? = null

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                movieAndActorsViewModel.movieDescription.collect { descriptionDto ->

                    if (descriptionDto?.type == "TV_SERIES") {
                        binding.seriesText.visibility = View.VISIBLE
                        binding.seasonsCount.visibility = View.VISIBLE
                        binding.seriesAll.visibility = View.VISIBLE
                    }
                    binding.shortDescription.text = descriptionDto?.shortDescription
                    binding.description.text = descriptionDto?.description
                    binding.nameEnglish.text =
                        if (descriptionDto?.nameOriginal.isNullOrEmpty()) descriptionDto?.nameRu?.uppercase() else descriptionDto?.nameOriginal?.uppercase()
                    binding.nameRussian.text = descriptionDto?.nameRu
                    binding.rating.text =
                        if (descriptionDto?.ratingKinopoisk == 0.0) "" else descriptionDto?.ratingKinopoisk?.toString()
                            ?: ""
                    val rating =
                        if (descriptionDto?.ratingAgeLimits == null) "" else descriptionDto.ratingAgeLimits.replace(
                            "age",
                            ""
                        ) + "+"
                    binding.age.text = rating
                    binding.genre.text = descriptionDto?.genres?.take(3)?.joinToString(", ") {
                        it.genre.toString()
                    }
                    binding.year.text = descriptionDto?.year?.toString() ?: ""
                    binding.country.text = descriptionDto?.countries?.take(1)?.joinToString {
                        it.country.toString()
                    } ?: ""
                    binding.time.text = "${descriptionDto?.filmLength ?: ""} мин"
                    binding.movieImage.load(descriptionDto?.posterUrl)

                    nameOriginal = descriptionDto?.nameOriginal ?: ""
                    nameEn = descriptionDto?.nameEn ?: ""
                    nameRu = descriptionDto?.nameRu ?: ""

                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                movieAndActorsViewModel.series.collect {

                    val episodeCount = it.sumOf {
                        it.episodes!!.size
                    }

                    binding.seasonsCount.text =
                        "Сезонов: ${it.size}, Серий: $episodeCount"

                    val list = it

                    binding.seriesAll.setOnClickListener {
                        val bundle = Bundle()
                        val seasonList = list as? ArrayList<out Parcelable>
                        bundle.putParcelableArrayList("seasonList", seasonList)
                        bundle.putString("nameEn", nameEn)
                        bundle.putString("nameOriginal", nameOriginal)
                        bundle.putString("nameRu", nameRu)

                        findNavController().navigate(R.id.seriesFragment, bundle)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                movieAndActorsViewModel.state.collect {
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