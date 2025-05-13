package com.amar.apidemomvvm.common

sealed class Result<out T> {
     data class Success<T>(val data: T) : Result<T>()
     data class Failure(val message: String) : Result<Nothing>()
     object Loading : Result<Nothing>()
}