package com.bfc.moviesearchapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bfc.moviesearchapp.R
import com.bfc.moviesearchapp.databinding.FragmentDetailsBinding
import com.bfc.moviesearchapp.util.downloadFromUrl
import com.bfc.moviesearchapp.util.placeHolderProgressBar
import com.bfc.moviesearchapp.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        viewModel.getDataFromAPI()

        observeLiveData()
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    private fun observeLiveData() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { movies->
            movies?.let {
                binding.movieTitle.text = movies.Title
                binding.movieDetail.text = movies.Type
                binding.movieImage.downloadFromUrl(movies.Poster, placeHolderProgressBar(binding.root.context))
            }
        })
    }

}