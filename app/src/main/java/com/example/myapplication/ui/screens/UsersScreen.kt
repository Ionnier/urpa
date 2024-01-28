package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.myapplication.R
import com.example.myapplication.ui.components.ColumnListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    viewModel: UsersViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Users")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                },
            )
        },
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null
                )
            }
        }
    ) {
        val userListItems = viewModel.users.collectAsLazyPagingItems()

        LazyColumn(modifier = Modifier.padding(it)) {
            items(
                count = userListItems.itemCount
            ) { index ->
                val item = userListItems[index]
                ColumnListItem(
                    title = item?.fullName!!,
                    subtitle = stringResource(
                        id = R.string.user_screen_subtitle,
                        item.age,
                        item.countryCode.uppercase()
                    ),
                    addon = item.time,
                    icon = item.avatarURL
                )
            }

            when (userListItems.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        }
                    }
                }

                else -> {}
            }
        }
    }
}