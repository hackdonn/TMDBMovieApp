package com.app.tmdbmovieapp.utils


import android.content.Context
import com.app.tmdbmovieapp.R

interface Error

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN,
}

fun NetworkError.toString(context: Context): String {
    val resId = when (this) {
        NetworkError.NO_INTERNET, NetworkError.REQUEST_TIMEOUT -> R.string.error_no_internet
        else -> R.string.error_something_went_wrong
    }
    return context.getString(resId)
}