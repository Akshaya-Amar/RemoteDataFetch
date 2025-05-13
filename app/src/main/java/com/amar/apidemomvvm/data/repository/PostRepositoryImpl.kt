package com.amar.apidemomvvm.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.amar.apidemomvvm.data.api.ApiService
import com.amar.apidemomvvm.data.model.Post
import com.amar.apidemomvvm.data.paging.PostPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
     private val apiService: ApiService
) : PostRepository {
     override fun getPosts(): Flow<PagingData<Post>> {
          return Pager(
               config = PagingConfig(pageSize = 10, initialLoadSize = 20),
               pagingSourceFactory = { PostPagingSource(apiService) }
          ).flow
     }
}