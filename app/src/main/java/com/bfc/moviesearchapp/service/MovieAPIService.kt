package com.bfc.moviesearchapp.service

import com.bfc.moviesearchapp.model.Movie
import com.bfc.moviesearchapp.model.Search
import com.bfc.moviesearchapp.view.SearchFragment
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {

    //https:omdbapi.com/
    //apikey=8644e6f

    private val BASE_URL = "https:omdbapi.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getData(movieName: String?) : Single<Movie> {
            return api.getMovies(movieName,"8644e6f")




    }

}