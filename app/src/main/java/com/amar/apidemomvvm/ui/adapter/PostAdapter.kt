package com.amar.apidemomvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amar.apidemomvvm.data.model.Post
import com.amar.apidemomvvm.databinding.PostItemBinding

class PostAdapter(
     private val onItemClick: (Post) -> Unit
) : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {

     class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
          override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
               return oldItem.id == newItem.id
          }

          override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
               return oldItem == newItem
          }
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
          val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return PostViewHolder(binding)
     }

     override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
          val post = getItem(position)
          holder.bind(post, onItemClick)
     }

     class PostViewHolder(
          private val binding: PostItemBinding
     ) : RecyclerView.ViewHolder(binding.root) {
          fun bind(post: Post, onItemClick: (Post) -> Unit) {
               val id = post.id?.toString()
               binding.postId.text = id
               binding.titleTextView.text = post.title
               binding.contentTextView.text = post.content

               binding.root.setOnClickListener {
                    onItemClick(post)
               }
          }
     }
}