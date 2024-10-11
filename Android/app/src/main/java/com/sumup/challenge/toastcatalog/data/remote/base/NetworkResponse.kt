package com.sumup.challenge.toastcatalog.data.remote.base

import androidx.annotation.Keep
import java.util.Date

sealed class NetworkResponse<out T : Any, out U : Any> {

    @Keep
    val timestamp = Date().time

    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    data class Error<U : Any>(val body: U?, val code: Int) : NetworkResponse<Nothing, Nothing>()

}