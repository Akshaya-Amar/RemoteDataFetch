package com.amar.apidemomvvm.data.repository

import com.amar.apidemomvvm.common.Result
import com.amar.apidemomvvm.data.api.ApiService
import com.amar.apidemomvvm.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
     private val apiService: ApiService
) : PostRepository {
     override suspend fun getPosts(): Result<List<Post>> = withContext(Dispatchers.IO) {
          try {
               val response = apiService.fetchPosts()
               if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                         Result.Success(it)
                    } ?: Result.Failure("Data is empty")
               } else {
                    Result.Failure(response.message() ?: "Something went wrong")
               }
          } catch (exception: Exception) {
               Result.Failure(exception.message ?: "Something went wrong")
          }
     }
}