package me.mantou.me.mantou.bunhut.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateCommentDto(
    val username: String,
    val comment: String,
)