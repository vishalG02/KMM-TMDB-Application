package com.vis.myapplication.repo

import com.vis.myapplication.api.TMDBApi
import com.vis.myapplication.model.MoviesWrapper

class MovieRepositoryImpl : MovieRepository {
    private val TMDBApi = TMDBApi()

    override suspend fun getMovies(region: String): MoviesWrapper {
        return TMDBApi.getAllMovies(region)
    }


}