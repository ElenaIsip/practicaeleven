package com.example.practicpo.data
data class Post(val id: Long,
                val author: String,
                val content: String,
                val published: String,
                var likedByMe: Boolean = false)
