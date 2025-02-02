package com.raji.movies.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.raji.movies.R
import com.raji.movies.ui.theme.Orange

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val icons = listOf(
        R.drawable.ic_video_play,
        R.drawable.ic_search_normal,
        R.drawable.ic_ticket,
        R.drawable.ic_user
    )
    NavigationBar(
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        icons.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Box(
                        modifier
                            .size(64.dp)
                            .background(
                                color = if (index == selectedItem) Orange else Color.Transparent,
                                shape = CircleShape
                            )
                            .clip(CircleShape), contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(item),
                            contentDescription = ""
                        )
                    }
                },
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.LightGray,
                    indicatorColor = MaterialTheme.colorScheme.primary
                ),


                )
        }
    }
}