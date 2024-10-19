package com.borjadelgadodev.cervezaymasapp.ui.screens.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.borjadelgadodev.cervezaymasapp.R
import com.borjadelgadodev.cervezaymasapp.R.string.address_label
import com.borjadelgadodev.cervezaymasapp.R.string.back
import com.borjadelgadodev.cervezaymasapp.R.string.brewery_type_label
import com.borjadelgadodev.cervezaymasapp.R.string.city_label
import com.borjadelgadodev.cervezaymasapp.R.string.country_label
import com.borjadelgadodev.cervezaymasapp.R.string.phone_label
import com.borjadelgadodev.cervezaymasapp.R.string.postal_code_label
import com.borjadelgadodev.cervezaymasapp.R.string.state_label
import com.borjadelgadodev.cervezaymasapp.R.string.website_label
import com.borjadelgadodev.cervezaymasapp.utils.Constants.RANDOM_IMAGE_URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: DetailViewModel, onBack: () -> Unit) {
    val state = viewModel.state

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = state.brewery?.name ?: "") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = back)
                    )
                }
            }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = R.drawable.brewery,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp)
            )

            if (state.loading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

            state.brewery?.let { brewery ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray)
                        .padding(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "${stringResource(id = address_label)}${brewery.street}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "${stringResource(id = city_label)}${brewery.city}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "${stringResource(id = country_label)}${brewery.country}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "${stringResource(id = state_label)}${brewery.state}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "${stringResource(id = postal_code_label)}${brewery.postal_code}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "${stringResource(id = brewery_type_label)}${brewery.brewery_type}", style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray)
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(id = website_label),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "${stringResource(id = website_label)}${brewery.website_url}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray)
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = stringResource(id = phone_label),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "${stringResource(id = phone_label)}${brewery.phone}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}