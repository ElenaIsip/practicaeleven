package com.example.practicpo.repository

import androidx.lifecycle.LiveData
import com.example.practicpo.activity.Post
interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun save(post: Post)
    fun removeById(id: Long)
}