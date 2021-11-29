package com.skycompose.skysportsapplication.data

sealed class SkySportsResult<out R> {
    data class Success<out T>(val data: T) : SkySportsResult<T>()
    data class Error(val exception: Exception) : SkySportsResult<Nothing>()
}

fun <T> SkySportsResult<T>.successOr(fallback: T): T {
    return (this as? SkySportsResult.Success<T>)?.data ?: fallback
}