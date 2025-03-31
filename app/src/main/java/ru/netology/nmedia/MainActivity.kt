package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.PostService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = 11111,
            share = 10000,
            views = 99999,
            likedByMe = false
        )

        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            likeCount.text = PostService.numToString(post.likes)
            shareCount.text = PostService.numToString(post.share)
            viewsCount.text = PostService.numToString(post.views)

            like.setImageResource(R.drawable.ic_like_24)

            like.setOnClickListener {
                post.likedByMe = !post.likedByMe

                like.setImageResource(
                    if (post.likedByMe) {
                        post.likes += 1
                        likeCount.text = PostService.numToString(post.likes)
                        R.drawable.ic_liked_24
                    } else {
                        post.likes -= 1
                        likeCount.text = PostService.numToString(post.likes)
                        R.drawable.ic_like_24
                    }
                )
            }

            share.setOnClickListener {
                post.share += 10
                shareCount.text = PostService.numToString(post.share)
            }
        }
    }


}