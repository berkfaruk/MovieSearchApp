package com.bfc.moviesearchapp.service

import com.bfc.moviesearchapp.model.Movie
import com.bfc.moviesearchapp.model.Search
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //https:omdbapi.com/
    //apikey=8644e6f

    @GET("/")
    fun getMovies(
        @Query("s") movieTitle: String?,
        @Query("apikey") apikey: String):Single<Movie>


}