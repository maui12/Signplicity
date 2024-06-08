package com.emprendimiento.signplicity

data class Post(
    var postId: String = "",
    var userId: String = "",
    var username: String = "",
    var content: String = "",
    var timestamp: Long = 0L,
    var likes: Int = 0,
    var comments: List<Comment> = emptyList()
)