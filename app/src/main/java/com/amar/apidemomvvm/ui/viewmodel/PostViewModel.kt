package com.amar.apidemomvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.apidemomvvm.common.Result
import com.amar.apidemomvvm.data.model.Post
import com.amar.apidemomvvm.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
     private val repo: PostRepository
) : ViewModel() {
     private var _posts = MutableLiveData<Result<List<Post>>>(Result.Loading)
     val posts: LiveData<Result<List<Post>>> get() = _posts

     init {
//          getNotes()
     }

      fun getNotes() {
          viewModelScope.launch {
               val postsResult = repo.getPosts()
               _posts.postValue(postsResult)
               val map = mapOf(
                    "sdf" to "sdf",
                    "abc" to "ABC",
                    "xyz" to "XYZ"
               )
               map.forEach { (t, u) ->
                    Log.d("check1...map", "getNotes: $t -> $u")
               }

               for ((key, value) in map) {
                    Log.d("check1...map traditional", "getNotes: $key -> $value")
               }

               for (key in map.keys) {
                    Log.d("check1...map keys", "getNotes: $key")
               }

               for (value in map.values) {
                    Log.d("check1...map values", "getNotes: $value")
               }

               if (map.containsKey("abc")) {
                    Log.d("check1...map containsKey", "getNotes: yes")
               }
          }
     }
}