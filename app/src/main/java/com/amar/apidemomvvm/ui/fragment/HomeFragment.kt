package com.amar.apidemomvvm.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.amar.apidemomvvm.data.model.Post
import com.amar.apidemomvvm.databinding.FragmentHomeBinding
import com.amar.apidemomvvm.ui.adapter.PostAdapter
import com.amar.apidemomvvm.ui.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
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

          viewLifecycleOwner.lifecycleScope.launch {
               repeatOnLifecycle(Lifecycle.State.STARTED) {
                    delay(4000)
                    viewmodel.posts.collectLatest {
                         postAdapter.submitData(it)
                    }
               }
          }

          viewLifecycleOwner.lifecycleScope.launch {
               repeatOnLifecycle(Lifecycle.State.STARTED) {
                    postAdapter.loadStateFlow.collectLatest { loadStates ->
                         when (loadStates.refresh) {
                              is LoadState.Loading -> {
                                   Log.d("check...load state", "onViewCreated: loading")
                                   binding.progressBar.visibility = View.VISIBLE
                                   binding.recyclerView.visibility = View.GONE
                              }

                              is LoadState.NotLoading -> {
                                   Log.d("check...load state", "onViewCreated: Success")
                                   binding.recyclerView.visibility = View.VISIBLE
                                   binding.progressBar.visibility = View.GONE
                              }

                              is LoadState.Error -> {
                                   Log.d("check...load state", "onViewCreated: Error")
                                   binding.recyclerView.visibility = View.GONE
                                   binding.progressBar.visibility = View.GONE
                                   val error = (loadStates.refresh as LoadState.Error).error
                                   val errorMessage = error.message?.takeIf { it.isNotBlank() } ?: "Something went wrong"
                                   Log.d("check...load state", "onViewCreated: Error $errorMessage")
                                   Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                              }
                         }
                    }
               }
          }
     }

     private fun showBottomSheet(post: Post) {
          Toast.makeText(requireContext(), post.id.toString(), Toast.LENGTH_SHORT).show()
     }
}