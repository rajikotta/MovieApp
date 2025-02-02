package com.raji.movies.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raji.movies.R
import com.raji.movies.ui.theme.Orange

@Composable
fun MovieSearchBar(modifier: Modifier = Modifier) {
    val searchQuery by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .minimumInteractiveComponentSize(),
            shape = RoundedCornerShape(12.dp),
            value = searchQuery,
            onValueChange = {},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = { },
            ),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Orange,
                focusedBorderColor = Color.White
            ),
            placeholder = {
                Text(text = stringResource(R.string.search_your_movies))
            },

            trailingIcon = {
                IconButton(
                    onClick = { },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search_normal),
                        tint = Orange,
                        contentDescription = "",
                    )
                }

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    MovieSearchBar()
}