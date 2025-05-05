package me.mantou.me.mantou.bunhut.service

import me.mantou.me.mantou.bunhut.dto.CreateCommentDto
import me.mantou.me.mantou.bunhut.model.CommentModel
import me.mantou.me.mantou.bunhut.repository.CommentsRepository

object CommentsService {
    fun getComments(): List<CommentModel> {
        return CommentsRepository.getComments()
    }

    fun addComment(comment: CreateCommentDto): Boolean {
        val model = CommentModel(null, comment.username.trim(), comment.comment.trim(), System.currentTimeMillis())
        CommentsRepository.addComment(model)
        return true
    }
}