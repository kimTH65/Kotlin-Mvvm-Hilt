package com.example.memo

import java.net.URLDecoder


data class ApiResult<out T>(val status: Status, val code: String?, val message: String?, val data: T?, val exception: Exception?) {

    companion object {
        fun <T> success(code: String, data: T?): ApiResult<T> {
            return ApiResult(Status.SUCCESS, code, "", data, null)
        }

        fun <T> error(code: String, message: String): ApiResult<T> {
            return ApiResult(Status.API_ERROR, URLDecoder.decode(code, "UTF-8"), URLDecoder.decode(message, "UTF-8"), null, null)
        }

        fun <T> error(exception: Exception?): ApiResult<T> {
            return ApiResult(Status.NETWORK_ERROR, null, null, null, exception)
        }

        fun <T> loading(): ApiResult<T> {
            return ApiResult(Status.LOADING, null, null, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    API_ERROR,
    NETWORK_ERROR,
    LOADING
}