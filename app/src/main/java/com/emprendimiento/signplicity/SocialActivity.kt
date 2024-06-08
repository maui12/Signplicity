package com.emprendimiento.signplicity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SocialActivity : AppCompatActivity() {
    private lateinit var postContentEditText: EditText
    private lateinit var postButton: Button
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social)

        postContentEditText = findViewById(R.id.postContentEditText)
        postButton = findViewById(R.id.postButton)
        postsRecyclerView = findViewById(R.id.postsRecyclerView)

        postsAdapter = PostsAdapter()
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        postsRecyclerView.adapter = postsAdapter


        postButton.setOnClickListener {

            val content = postContentEditText.text.toString()
            val currentUser = FirebaseAuth.getInstance().currentUser
            val userId = currentUser?.uid ?: ""
            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
            databaseReference.child(userId).get().addOnSuccessListener {
                if(it.exists()) {
                    val username = it.child("username").value.toString()
                    val post = Post(userId = userId, username = username, content = content, timestamp = System.currentTimeMillis())
                    FirebaseUtil.savePost(post) { success ->
                        if (success) {
                            postContentEditText.text.clear()
                            postsAdapter.addPost(post)
                        }
                    }
                }
            }
        }

        loadPosts()
    }

    private fun loadPosts() {
        FirebaseUtil.getPosts { posts ->
            postsAdapter.submitList(posts)
        }
    }
}