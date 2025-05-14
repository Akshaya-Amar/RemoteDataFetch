package com.amar.apidemomvvm.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amar.apidemomvvm.databinding.LoaderItemBinding

class LoaderAdapter(
     private val onRetry: () -> Unit
) : LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {
     override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
          val binding =
               LoaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return LoaderViewHolder(binding, onRetry)
     }

     override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
          holder.bind(loadState)
     }

     class LoaderViewHolder(
          private val binding: LoaderItemBinding,
          val onRetry: () -> Unit
     ) : RecyclerView.ViewHolder(binding.root) {

          init {
               binding.retryButton.setOnClickListener {
                    onRetry()
                    binding.retryButton.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
               }
          }

          fun bind(loadState: LoadState) {
               Log.d("check...loadstate", "bind: $loadState")
               binding.progressBar.isVisible = loadState is LoadState.Loading
               binding.retryButton.isVisible = loadState is LoadState.Error
          }
     }
}