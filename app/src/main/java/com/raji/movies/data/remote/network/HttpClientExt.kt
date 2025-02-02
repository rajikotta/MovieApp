package com.raji.movies.data.remote.network

import kotlinx.coroutines.ensureActive
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext


suspend inline fun <reified D : Any> performRequest(request: () -> Response<D>): Result<D, DataError> {

    val response = try {
        request()
    } catch (e: SocketTimeoutException) {
        return Result.Error(DataError.Remote.REQUEST_TIME_OUT)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }
    return response.toResult()
}


inline fun <reified D> Response<D>.toResult(): Result<D, DataError> {
    return when (code()) {
        in 200..299 ->
            try {
                Result.Success(body()!!)
            } catch (e: Exception) {
                Result.Error(DataError.Remote.SERIALIZATION)
            }

        408 -> Result.Error(DataError.Remote.REQUEST_TIME_OUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 ->
            try {
                val errorString = errorBody()!!.string()
                val errorMessage = JSONObject(errorString).getString("status_message")
                Result.Error(DataError.ApiError(errorMessage))
            } catch (e: Exception) {
                Result.Error(DataError.Remote.SERVER)

            }

        else -> Result.Error(DataError.Remote.UNKNOWN)
    }
}