package com.ajidres.movies.extentions

import com.ajidres.movies.domain.ResponseErrorUI
import java.io.IOException
import retrofit2.HttpException
import retrofit2.Response

const val IO_EXCEPTION_CODE = -100
const val UNEXPECTED_ERROR_CODE = -101

fun <T : Any> Response<T>.bodyOrException(): T {
    val body = body()
    if (body != null && isSuccessful) {
        return body
    } else {
        throw HttpException(this)
    }
}

fun Throwable.getErrorResponse(): ResponseErrorUI {

    val error = when (this) {
        is HttpException -> {
            this.code()
        }

        is IOException -> {
            IO_EXCEPTION_CODE
        }

        else -> {
            UNEXPECTED_ERROR_CODE
        }
    }

    return ResponseErrorUI(
        errorCode = error.toString(), description = this.message ?: ""
    )

}
