package com.amar.apidemomvvm.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.amar.apidemomvvm.R
import com.amar.apidemomvvm.common.Result
import com.amar.apidemomvvm.ui.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

//     private val viewmodel: PostViewModel by viewModels()

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_home)

          /*viewmodel.posts.observe(this) { result ->
               when (result) {
                    is Result.Success -> {
                         Log.d("check...", "onCreate: Success")
                         val data = result.data
                         data.forEach { post ->
                              Log.d("check...", "onCreate: ${post.id}")
                         }
                    }

                    is Result.Failure -> {
                         Log.d("check...", "onCreate: Failure -> ${result.message}")
                    }

                    Result.Loading -> {
                         Log.d("check...", "onCreate: Loading")
                    }
               }

          }
*/
//          Log.d(TAG, "onCreate: ")
     }
}