package com.sumup.challenge.toastcatalog.domain.model

import com.sumup.challenge.toastcatalog.data.remote.base.ErrorResponse
import com.sumup.challenge.toastcatalog.data.remote.base.NetworkResponse

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(
        val errorCode: String,
        val reason: Throwable? = null,
    ) : Result<Nothing>()

    companion object {
        fun error(error: NetworkResponse.Error<*>): Error {
            return when (error.body) {
                is Throwable -> Error(ErrorResponse.UNKNOWN.errorCode, error.body)
                else -> Error(ErrorResponse.UNKNOWN.errorCode)
            }
        }

        fun error(
            reason: Throwable? = null,
            errorCode: String = ErrorResponse.UNKNOWN.errorCode,
        ): Error {
            return Error(errorCode, reason)
        }
    }
}