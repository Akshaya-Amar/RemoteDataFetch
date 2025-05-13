package com.amar.apidemomvvm.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amar.apidemomvvm.common.Result
import com.amar.apidemomvvm.data.model.Post
import com.amar.apidemomvvm.databinding.FragmentHomeBinding
import com.amar.apidemomvvm.ui.adapter.PostAdapter
import com.amar.apidemomvvm.ui.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
     private lateinit var binding: FragmentHomeBinding
     private val viewmodel: PostViewModel by viewModels()
     private val postAdapter by lazy {
          PostAdapter { post ->
               showBottomSheet(post)
          }
     }

     override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View {
          binding = FragmentHomeBinding.inflate(inflater, container, false)
          return binding.root
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)

          binding.recyclerView.apply {
               layoutManager = LinearLayoutManager(requireContext())
               adapter = postAdapter
          }

          lifecycleScope.launch {
               Log.d("check...ddd", "onViewCreated: before")
               delay(5000)
               Log.d("check...ddd", "onViewCreated: after")
               viewmodel.getNotes()
          }

          viewmodel.posts.observe(viewLifecycleOwner) { result ->
               when (result) {
                    is Result.Success -> {
                         Log.d("check...", "onCreate: Success")
                         val data = result.data
                         postAdapter.submitList(data)
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
     }

     private fun showBottomSheet(post: Post) {
          Toast.makeText(requireContext(), post.id.toString(), Toast.LENGTH_SHORT).show()
     }
}