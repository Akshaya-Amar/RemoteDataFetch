package com.amar.apidemomvvm.data.repository

import com.amar.apidemomvvm.common.Result
import com.amar.apidemomvvm.data.model.Post

interface PostRepository {
     suspend fun getPosts(): Result<List<Post>>
}