package com.bfc.moviesearchapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bfc.moviesearchapp.model.Movie
import com.bfc.moviesearchapp.model.Search
import com.bfc.moviesearchapp.service.MovieAPI
import com.bfc.moviesearchapp.service.MovieAPIService
import com.bfc.moviesearchapp.view.SearchFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SearchViewModel : ViewModel() {
    val movies = MutableLiveData<Movie>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    private val movieApiService = MovieAPIService()
    private val disposable = CompositeDisposable()



    fun getDataFromAPI(movieName: String?) {
        movieLoading.value = true

        disposable.add(
            movieApiService.getData(movieName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Movie>(){
                    override fun onSuccess(t: Movie) {

                        movies.value = t
                        movieError.value = false
                        movieLoading.value = false

                    }

                    override fun onError(e: Throwable) {
                        movieLoading.value = false
                        movieError.value = true
                        Log.d("failuremessage","onError: " + e.toString())
                    }
                })
        )
    }


}