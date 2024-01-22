package com.bfc.moviesearchapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bfc.moviesearchapp.model.Search
import com.bfc.moviesearchapp.util.MySingleton

class DetailsViewModel : ViewModel() {

    val movieLiveData = MutableLiveData<Search?>()

    fun getDataFromAPI() {
        val movie = MySingleton.selectedMovie
        movie?.let {
            movieLiveData.value = movie
        }
    }

}