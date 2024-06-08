package com.emprendimiento.signplicity

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

object FirebaseUtil {
    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("users")
    private val postsRef = database.getReference("posts")

    fun saveUser(user: User, onComplete: (Boolean) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let {
            usersRef.child(it.uid).setValue(user)
                .addOnCompleteListener { task ->
                    onComplete(task.isSuccessful)
                }
        } ?: onComplete(false)
    }

    fun getUser(userId: String, onComplete: (User?) -> Unit) {
        usersRef.child(userId).get().addOnSuccessListener {
            onComplete(it.getValue(User::class.java))
        }.addOnFailureListener {
            onComplete(null)
        }
    }

    fun savePost(post: Post, onComplete: (Boolean) -> Unit) {
        val postId = postsRef.push().key ?: ""
        post.postId = postId
        postsRef.child(postId).setValue(post)
            .addOnCompleteListener { task ->
                onComplete(task.isSuccessful)
            }
    }

    fun getPosts(onComplete: (List<Post>) -> Unit) {
        postsRef.orderByChild("timestamp").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val posts = dataSnapshot.children.reversed().mapNotNull { snapshot ->
                    snapshot.getValue(Post::class.java)
                }
                onComplete(posts)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                onComplete(emptyList())
            }
        })
    }

    fun addLike(postId: String, onComplete: (Boolean) -> Unit) {
        postsRef.child(postId).child("likes").get().addOnSuccessListener { snapshot ->
            val likes = snapshot.getValue(Int::class.java) ?: 0
            postsRef.child(postId).child("likes").setValue(likes + 1)
                .addOnCompleteListener { task ->
                    onComplete(task.isSuccessful)
                }
        }
    }

    fun addComment(postId: String, comment: Comment, onComplete: (Boolean) -> Unit) {
        val commentId = postsRef.child(postId).child("comments").push().key ?: ""
        comment.commentId = commentId
        postsRef.child(postId).child("comments").child(commentId).setValue(comment)
            .addOnCompleteListener { task ->
                onComplete(task.isSuccessful)
            }
    }
}