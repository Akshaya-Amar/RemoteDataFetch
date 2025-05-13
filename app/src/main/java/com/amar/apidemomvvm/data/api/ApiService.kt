package com.amar.apidemomvvm.data.api

import com.amar.apidemomvvm.data.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
     @GET("/posts")
     suspend fun fetchPosts(): Response<List<Post>>
}