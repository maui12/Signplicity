// Comment.kt
package com.emprendimiento.signplicity

data class Comment(
    var commentId: String = "",
    var userId: String = "",
    var content: String = "",
    var timestamp: Long = 0L
)