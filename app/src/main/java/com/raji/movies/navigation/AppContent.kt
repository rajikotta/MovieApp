package com.raji.movies.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.raji.movies.R
import com.raji.movies.presentation.BottomNavigationBar
import com.raji.movies.presentation.home.HomeScreenRoot
import com.raji.movies.presentation.home.HomeViewModel
import com.raji.movies.presentation.search.SearchScreenRoot
import com.raji.movies.presentation.settings.SettingsScreenRoot
import com.raji.movies.presentation.ticket.TicketScreenRoot


data class BottomNavItem(val route: Routes, @DrawableRes val icon: Int)

val menus = listOf(
    BottomNavItem(Routes.Home, R.drawable.ic_video_play),
    BottomNavItem(Routes.Search, R.drawable.ic_search_normal),
    BottomNavItem(Routes.Ticket, R.drawable.ic_ticket),
    BottomNavItem(Routes.Settings, R.drawable.ic_user)
)


@Composable
fun AppContent() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        bottomBar = {
            BottomNavigationBar(selectedItem = selectedItem) { selectedIndex ->
                selectedItem = selectedIndex
                navController.navigate(menus[selectedItem].route) {
                    launchSingleTop = true

                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(innerPadding)
        ) {

            NavHost(navController = navController, startDestination = Routes.MovieGraph) {
                navigation<Routes.MovieGraph>(startDestination = Routes.Home) {
                    composable<Routes.Home> {
                        val homeViewModel = hiltViewModel<HomeViewModel>()
                        HomeScreenRoot(viewModel = homeViewModel)
                    }
                    composable<Routes.Search> {
                        SearchScreenRoot()
                    }
                    composable<Routes.Ticket> {
                        TicketScreenRoot()
                    }
                    composable<Routes.Settings> {
                        SettingsScreenRoot()
                    }
                }

            }

        }

    }
}