package me.mantou.me.mantou.bunhut.model

import kotlinx.serialization.Serializable

@Serializable
data class CommentModel(val avatarUrl: String?, val author: String, val comment: String, val createdTime: Long) {
}