package com.amar.apidemomvvm.data.api

import com.amar.apidemomvvm.data.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
     @GET("/posts")
     suspend fun fetchPosts(
          @Query("_start") start: Int,
          @Query("_limit") limit: Int
     ): Response<List<Post>>
}