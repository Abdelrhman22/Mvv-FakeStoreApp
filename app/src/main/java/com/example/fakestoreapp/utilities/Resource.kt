package com.example.fakestoreapp.utilities

data class Resource<out T>
constructor(
    val status: Status, val response: T? = null, val error: Throwable? = null
) {
    companion object {

        fun <T> success(response: T?): Resource<T> {
            return Resource(status = Status.SUCCESS, response = response, error = null)
        }

        fun <T> error(error: Throwable?): Resource<T> {
            return Resource(status = Status.ERROR, response = null, error = error)
        }

        fun <T> loading(): Resource<T> {
            return Resource(status = Status.LOADING, response = null, error = null)
        }

        fun <T> noInternet(): Resource<T> {
            return Resource(status = Status.NO_INTERNET, response = null, error = null)
        }
    }
}