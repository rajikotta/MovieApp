package com.raji.movies.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable data object MovieGraph:Routes


    @Serializable
    data object Home : Routes
    @Serializable
    data object Search : Routes
    @Serializable
    data object Ticket : Routes
    @Serializable
    data object Settings : Routes
}