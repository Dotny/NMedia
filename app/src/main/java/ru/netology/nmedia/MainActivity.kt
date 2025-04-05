package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.PostService

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                content.text = post.content
                published.text = post.published
                likeCount.text = PostService.numToString(post.likes)
                shareCount.text = PostService.numToString(post.share)
                viewsCount.text = PostService.numToString(post.views)

                like.setImageResource(R.drawable.ic_like_24)
                like.setImageResource(
                    if (post.likedByMe) {
                        likeCount.text = PostService.numToString(post.likes)
                        R.drawable.ic_liked_24
                    } else {
                        likeCount.text = PostService.numToString(post.likes)
                        R.drawable.ic_like_24
                    }
                )

            }
        }

        binding.like.setOnClickListener {
            viewModel.like()
        }

        binding.share.setOnClickListener {
            viewModel.share()
        }
    }


}