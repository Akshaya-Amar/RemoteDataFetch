package com.amar.apidemomvvm.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amar.apidemomvvm.data.api.ApiService
import com.amar.apidemomvvm.data.model.Post

class PostPagingSource(
     private val apiService: ApiService
) : PagingSource<Int, Post>() {
     override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
          val start = params.key ?: 0
          val limit = params.loadSize
          return try {
               val response = apiService.fetchPosts(start, limit)
               if (response.isSuccessful) {
                    val data = response.body() ?: emptyList()
                    LoadResult.Page(
                         data = data,
                         prevKey = if (start == 0) null else start - limit,
                         nextKey = if (data.isEmpty()) null else start + limit
                    )
               } else {
                    LoadResult.Error(Exception(response.message().takeIf { it.isNotBlank() } ?: "Something went wrong"))
               }
          } catch (exception: Exception) {
               LoadResult.Error(exception)
          }
     }

     override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
          return state.anchorPosition
     }
}