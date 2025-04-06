package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.PostService

typealias onLikeListener = (Post) -> Unit
typealias onShareListener = (Post) -> Unit

class PostsAdapter(private val onLikeListener: onLikeListener, private val onShareListener: onShareListener): ListAdapter<Post, PostViewHolder>(PostDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(view, onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostViewHolder(private val binding: CardPostBinding, private val onLikeListener: onLikeListener, private val onShareListener: onShareListener): RecyclerView.ViewHolder(binding.root){

    fun bind(post: Post) = with(binding){
        author.text = post.author
        content.text = post.content
        published.text = post.published
        likeCount.text = PostService.numToString(post.likes)
        shareCount.text = PostService.numToString(post.share)
        viewsCount.text = PostService.numToString(post.views)
        like.setImageResource(
            if (post.likedByMe) {
                likeCount.text = PostService.numToString(post.likes)
                R.drawable.ic_liked_24
            } else {
                likeCount.text = PostService.numToString(post.likes)
                R.drawable.ic_like_24
            }
        )
        like.setOnClickListener {
            onLikeListener(post)
        }
        share.setOnClickListener {
            onShareListener(post)
        }
    }
}

object PostDiffCallback: DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
}