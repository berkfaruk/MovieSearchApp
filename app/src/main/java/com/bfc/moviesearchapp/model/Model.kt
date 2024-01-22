package com.bfc.moviesearchapp.model

data class Movie(
    val Response: String,
    val Search: ArrayList<Search>,
    val totalResults: String
)

data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)