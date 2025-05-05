package me.mantou.me.mantou.bunhut.repository

import me.mantou.me.mantou.bunhut.model.CommentModel

object CommentsRepository {
    private val comments = mutableListOf<CommentModel>()

    fun addComment(comment: CommentModel): Boolean {
        comments.add(comment)
        return true
    }

    fun getComments() = comments.toList()
}