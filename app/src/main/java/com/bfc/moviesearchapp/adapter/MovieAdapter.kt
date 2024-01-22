package com.bfc.moviesearchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bfc.moviesearchapp.databinding.ItemMovieBinding
import com.bfc.moviesearchapp.model.Movie
import com.bfc.moviesearchapp.model.Search
import com.bfc.moviesearchapp.util.MySingleton
import com.bfc.moviesearchapp.util.downloadFromUrl
import com.bfc.moviesearchapp.util.placeHolderProgressBar
import com.bfc.moviesearchapp.view.SearchFragmentDirections

class MovieAdapter(val movieList: ArrayList<Search>?): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (movieList == null) {
            return 0
        } else {
            return movieList.size
        }


    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            if (movieList != null) {
                holder.binding.title.text = movieList[position].Title
                holder.binding.year.text = movieList[position].Year
                holder.binding.imageView.downloadFromUrl(movieList[position].Poster, placeHolderProgressBar(holder.itemView.context))


                holder.itemView.setOnClickListener {
                    val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment()
                    Navigation.findNavController(it).navigate(action)
                    MySingleton.selectedMovie = movieList.get(position)

                }
            }


    }

    fun updateMovieList(newMovieList: ArrayList<Search>?) {
            if (movieList != null) {
                movieList.clear()
                if (newMovieList != null) {
                    movieList.addAll(newMovieList)
                }
            }


    }
}