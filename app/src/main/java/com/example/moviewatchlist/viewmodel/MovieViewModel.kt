package com.example.moviewatchlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviewatchlist.data.MoviesData
import com.example.moviewatchlist.model.repository.MoviesService

class MovieViewModel : ViewModel() {
    private val moviesService: MoviesService = MoviesService().getInstance()
    private val _allMovies = MutableLiveData<List<MoviesData>>()
    val allMovies : LiveData<List<MoviesData>> = _allMovies

    init {
        moviesService.loadMovies(_allMovies)
    }
}