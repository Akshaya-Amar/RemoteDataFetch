package com.amar.apidemomvvm.data.repository

import androidx.paging.PagingData
import com.amar.apidemomvvm.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
     fun getPosts(): Flow<PagingData<Post>>
}