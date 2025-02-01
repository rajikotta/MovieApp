package com.raji.movies.data.network

interface AppError

sealed interface DataError : AppError {
    enum class Remote : DataError {
        REQUEST_TIME_OUT, TOO_MANY_REQUESTS, NO_INTERNET, SERVER, SERIALIZATION, UNKNOWN
    }

    enum class Local : DataError {
        DISK_FULL, UNKNOWN
    }
}

sealed interface Result<out D, out E : AppError> {
    data class Success<D>(val data: D) : Result<D, Nothing>
    data class Error<out E : AppError>(val error: E) : Result<Nothing, E>
}


inline fun <D, E : AppError> Result<D, E>.onSuccess(action: (D) -> Unit): Result<D, E> {
    return when (this) {
        is Result.Success -> {
            action(data)
            this
        }

        is Result.Error -> this
    }
}


inline fun <D, E : AppError> Result<D, E>.onError(action: (E) -> Unit): Result<D, E> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }

        is Result.Success -> this
    }
}

inline fun <D, E : AppError, R> Result<D, E>.map(map: (D) -> R): Result<R, E> {

    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> {
            Result.Success(map(data))
        }
    }


}