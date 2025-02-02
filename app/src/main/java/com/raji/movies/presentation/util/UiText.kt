package com.raji.movies.presentation.util

import android.content.Context
import androidx.annotation.StringRes

sealed interface UiText {
    data class DynamicString(val value: String) : UiText
    class StringResourceId(@StringRes val id: Int, vararg val args: String) : UiText

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> this.value
            is StringResourceId -> context.getString(this.id, *args)

        }
    }
}