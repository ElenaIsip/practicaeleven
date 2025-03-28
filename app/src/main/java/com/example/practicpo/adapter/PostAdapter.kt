package com.example.practicpo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practicpo.R
import com.example.practicpo.databinding.ActivitymakBinding
import com.example.practicpo.activity.Post

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
    fun onShare(post: Post) {}
}

class PostsAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ActivitymakBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

private var likesCount = 0
private var sharesCount = 0
private var viewsCount = 0
private var liked = false

class PostViewHolder(
    private val binding: ActivitymakBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like2.isChecked = post.likedByMe
            like2.text = "${post.likes}"

            if (post.video == null){
                videoView.visibility = View.GONE
                videoBtn.visibility = View.GONE
            }
            videoBtn.setOnClickListener{
                post.video?.let { it1 -> videoView.loadUrl(it1) }
            }


            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }

                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }

            like2.setOnClickListener {
                onInteractionListener.onLike(post)
            }
            binding.like2.setOnClickListener {
                liked = !liked
                if (liked) {
                    likesCount++

                } else {
                    likesCount--

                }
                binding.likesCount2.text = formatCount(likesCount)
            }

            binding.share2.setOnClickListener {
                sharesCount++
                binding.shareCount2.text = formatCount(sharesCount)
            }

            viewsCount = 999999
            binding.viewCount2.text = formatCount(viewsCount)
        }
    }

    private fun formatCount(count: Int): String {
        return when {
            count >= 1_000_000 -> String.format("%.1fM", count.toDouble() / 1_000_000)
            count >= 1_000 -> String.format("%dK", count / 1_000)
            else -> count.toString()
        }
    }

}


class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}



