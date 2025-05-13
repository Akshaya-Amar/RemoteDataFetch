package com.amar.apidemomvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.amar.apidemomvvm.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
     repo: PostRepository
) : ViewModel() {
     val posts = repo.getPosts().cachedIn(viewModelScope)
}

/*val map = mapOf(
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
          }*/