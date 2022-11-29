package br.com.las.tvmazechallenge.presentation.scenes.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.las.data.repositories.models.TVShowModel
import br.com.las.tvmazechallenge.R
import br.com.las.tvmazechallenge.ui.theme.cardElevation
import br.com.las.tvmazechallenge.ui.theme.minPadding
import coil.compose.rememberImagePainter

@Composable
internal fun mainScreenView(viewModel: MainScreenViewModel) {
    when (val screenState = viewModel.screenState.collectAsState().value) {
        is MainScreenViewModel.FetchState.Idle -> Unit
        is MainScreenViewModel.FetchState.Success -> {
            if(screenState.tvShows.isNotEmpty()) {
                listTVShows(screenState.tvShows,
                    { viewModel.onRequestMoreData() }) {
                    viewModel.onItemClicked(it)
                }
            }
        }
    }
}

@Composable
fun listTVShows(
    showList: List<TVShowModel>,
    requestMoreData: () -> Unit,
    itemClickListener: (TVShowModel) -> Unit
) {
    val almostEndingIndex = (showList.size * 0.9).toInt()

    LazyVerticalGrid(
        columns = GridCells.Fixed(integerResource(id = R.integer.grid_number_items)),
        contentPadding = PaddingValues(minPadding)
    ) {
        items(showList) { show ->
            if (show == showList[almostEndingIndex]) {
                requestMoreData()
            }
            TVShowCard(show.name, show.thumbnailUrl) { itemClickListener(show) }
        }
    }
}

@Composable
fun TVShowCard(showName: String, iconUrl: String?, onClickListener: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(cardElevation),
        modifier = Modifier
            .padding(minPadding)
            .clickable { onClickListener() }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val painter = rememberImagePainter(data = iconUrl)

            Image(
                painter = painter,
                contentDescription = showName,
                modifier = Modifier.aspectRatio(3 / 4f),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )

            Spacer(modifier = Modifier.height(minPadding))

            Text(
                text = showName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}