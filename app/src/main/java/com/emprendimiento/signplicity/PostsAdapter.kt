// PostsAdapter.kt
package com.emprendimiento.signplicity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    private var posts: MutableList<Post> = mutableListOf()

    fun submitList(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    fun addPost(post: Post) {
        posts.add(0, post)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)

        val timestamp = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date(post.timestamp))
        holder.timestampTextView.text = timestamp

        holder.usernameTextView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SeeUserActivity::class.java)
            intent.putExtra("userId", post.userId) // Pasa la userId como extra
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = posts.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postContentTextView: TextView = itemView.findViewById(R.id.postContentTextView)
        private val likeButton: Button = itemView.findViewById(R.id.likeButton)
        val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
        private val likesCountTextView: TextView = itemView.findViewById(R.id.likesCountTextView)
        val timestampTextView: TextView = itemView.findViewById(R.id.tvTimestamp)

        fun bind(post: Post) {
            postContentTextView.text = post.content
            usernameTextView.text = post.username
            likesCountTextView.text = post.likes.toString()

            likeButton.setOnClickListener {
                FirebaseUtil.addLike(post.postId) { success ->
                    if (success) {
                        // handle like success
                    }
                }
            }
        }
    }
}
