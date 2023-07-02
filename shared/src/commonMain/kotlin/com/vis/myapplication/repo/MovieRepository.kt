package com.vis.myapplication.repo

import com.vis.myapplication.model.MoviesWrapper

interface MovieRepository {
    suspend fun getMovies(region: String): MoviesWrapper

}