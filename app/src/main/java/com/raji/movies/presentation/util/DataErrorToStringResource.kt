package com.raji.movies.presentation.util

import com.raji.movies.R
import com.raji.movies.data.remote.network.DataError

fun DataError.toUiText(): UiText {

    return when (this) {
        is DataError.ApiError -> UiText.DynamicString(this.errorMessage)
        DataError.Local.DISK_FULL -> UiText.StringResourceId(R.string.error_disk_full)
        DataError.Local.UNKNOWN -> UiText.StringResourceId(R.string.error_unknown)
        DataError.Remote.REQUEST_TIME_OUT -> UiText.StringResourceId(R.string.error_request_timeout)
        DataError.Remote.TOO_MANY_REQUESTS -> UiText.StringResourceId(R.string.error_too_many_requests)
        DataError.Remote.NO_INTERNET -> UiText.StringResourceId(R.string.error_no_internet)
        DataError.Remote.SERVER -> UiText.StringResourceId(R.string.error_unknown)
        DataError.Remote.SERIALIZATION -> UiText.StringResourceId(R.string.error_serialization)
        DataError.Remote.UNKNOWN -> UiText.StringResourceId(R.string.error_unknown)
    }

}