package com.bfc.moviesearchapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bfc.moviesearchapp.adapter.MovieAdapter
import com.bfc.moviesearchapp.databinding.FragmentSearchBinding
import com.bfc.moviesearchapp.viewmodel.SearchViewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : SearchViewModel
    private lateinit var movieAdapter : MovieAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)


        observeLiveData()

        searchButton()

        binding.movieLoading.visibility = View.GONE
        binding.movieError.text = "Enter Movie Name"


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun observeLiveData() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {movies ->

        movies?.let {
            movieAdapter = MovieAdapter(arrayListOf())
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = movieAdapter
            binding.recyclerView.visibility = View.VISIBLE
            movieAdapter.updateMovieList(movies.Search)

        }

        })

        viewModel.movieError.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                if (it) {
                    binding.movieError.visibility = View.VISIBLE
                } else {
                    binding.movieError.visibility = View.GONE
                }
            }
        })

        viewModel.movieLoading.observe(viewLifecycleOwner, Observer {loading ->
            loading?.let {
                if (it) {
                    binding.movieLoading.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.movieError.visibility = View.GONE
                } else {
                    binding.movieLoading.visibility = View.GONE
                }
            }
        })




    }

    fun searchButton() {
        binding.searchButton.setOnClickListener {
            val movieNameText = binding.searchText.text.toString()
            if (movieNameText == "") {
                binding.movieError.text = "Please Enter Movie Name!"
                binding.movieLoading.visibility = View.GONE
            } else {
                viewModel.getDataFromAPI(movieNameText)
            }



        }

    }




}



