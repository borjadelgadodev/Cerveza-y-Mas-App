package com.borjadelgadodev.cervezaymasapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.borjadelgadodev.cervezaymasapp.R
import com.borjadelgadodev.cervezaymasapp.R.string.*
import com.borjadelgadodev.cervezaymasapp.data.Brewery
import com.borjadelgadodev.cervezaymasapp.ui.theme.CervezaYMasAppTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    CervezaYMasAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onClick: (Brewery) -> Unit, viewModel: HomeViewModel = viewModel()) {

    viewModel.onUiReady()

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(title_breweries)) },
                    scrollBehavior = scrollBehavior,
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->

            val state = viewModel.state
            if (state.loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }

            LazyColumn(
                contentPadding = padding
            ) {
                items(state.breweries) { brewery ->
                    BreweryItem(brewery = brewery, onClick = { onClick(brewery) })
                }
            }
        }
    }
}

@Composable
private fun BreweryItem(brewery: Brewery, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Gray)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_beer),
            contentDescription = "Beer Icon",
            modifier = Modifier
                .size(70.dp)
                .padding(8.dp)
        )
        Text(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            text = brewery.name,
            style = MaterialTheme.typography.bodyLarge
                .copy(color = Color.Black)
        )
    }
}