package me.mantou.me.mantou.bunhut.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(val code: Int = ApiCodes.SUCCESS, val data: T? = null) {
}

fun <T> T.wrapSuccess(): ApiResponse<T> {
    return ApiResponse(ApiCodes.SUCCESS, this)
}

fun <T> T.wrapFailure(): ApiResponse<T> {
    return ApiResponse(ApiCodes.FAILURE, this)
}

fun Boolean.wrapResponse(): ApiResponse<Boolean> {
    return ApiResponse(if (this) ApiCodes.SUCCESS else ApiCodes.FAILURE)
}

object ApiCodes {
    const val SUCCESS = 200
    const val FAILURE = 300
}

