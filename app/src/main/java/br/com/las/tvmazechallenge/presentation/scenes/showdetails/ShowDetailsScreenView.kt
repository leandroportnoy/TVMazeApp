package br.com.las.tvmazechallenge.presentation.scenes.showdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.las.commom.extensions.stripHtmlOut
import br.com.las.data.data.Schedule
import br.com.las.data.repositories.enum.ShowStatus
import br.com.las.data.repositories.models.EpisodeModel
import br.com.las.data.repositories.models.TVShowModel
import br.com.las.tvmazechallenge.ui.theme.*
import coil.compose.rememberImagePainter
import java.lang.Math.ceil
import java.lang.Math.floor

@Composable
internal fun ShowDetailsScreenView(viewModel: ShowDetailsScreenViewModel) {
    when (val screenState = viewModel.screenState.collectAsState().value) {
        is ShowDetailsScreenViewModel.ScreenState.Loading -> Unit
        is ShowDetailsScreenViewModel.ScreenState.Fetched -> ShowTVShow(
            screenState.tvShowDetail,
            screenState.episodes
        ) {
            viewModel.onEpisodeClicked(it)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ShowTVShow(
    tvShowDetail: TVShowModel,
    episodes: List<List<EpisodeModel>>?,
    onEpisodeClicked: (Long) -> Unit
) {
    LazyColumn {
        item {
            Header(
                showName = tvShowDetail.name,
                status = tvShowDetail.status,
                premiered = tvShowDetail.premiered,
                ended = tvShowDetail.ended,
                schedule = null,
                iconUrl = tvShowDetail.imageUrl
            )
        }

        item {
            Summary(
                showSummary = tvShowDetail.summary,
                genres = tvShowDetail.genres,
                rating = tvShowDetail.rating,
                modifier = Modifier.padding(minPadding)
            )
        }

    }
}

@Composable
private fun Header(
    showName: String,
    status: ShowStatus,
    premiered: String?,
    ended: String?,
    schedule: Schedule?,
    iconUrl: String?
) {
    Row {

    }
    val painter = rememberImagePainter(data = iconUrl)

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.TopCenter)
        ) {
            Row {
                Image(
                    painter = painter,
                    contentDescription = showName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Black)
                        .height(220.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )
                Divider(modifier = Modifier.height(minPadding))
            }

            Row {
                Text(
                    text = showName,
                    color = Color.Black,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(8.dp)
                )

                val statusColor = when (status) {
                    ShowStatus.RUNNING -> Green_800
                    ShowStatus.ENDED -> Red_800
                    else -> Color.Black
                }

                Text(
                    modifier = Modifier
                        .background(statusColor, RoundedCornerShape(4.dp))
                        .align(alignment = Alignment.CenterVertically),
                    color = Grey_50,
                    text = status.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }
}

@Composable
private fun Summary(
    showSummary: String?,
    rating: Float,
    genres: List<String>?,
    modifier: Modifier = Modifier
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.TopCenter)
        ) {

            Row {
                genres?.apply {
                    Genres(genres)
                }
            }

            Row {
                RatingBar(rating = rating.toString().toDouble())
            }

            Row {
                Text(
                    text = showSummary?.stripHtmlOut() ?: "",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }


        }
//        }
    }
}

@Composable
private fun Genres(genres: List<String>) {
    Row {
        genres.forEach {
            Box(
                modifier =
                Modifier.padding(
                    PaddingValues(start = 8.dp, top = 2.dp)
                )
            ) {
                Text(
                    modifier = Modifier
                        .background(Yellow_600, RoundedCornerShape(4.dp))
                        .padding(5.dp),
                    maxLines = 1,
                    color = Grey_900,
                    text = it,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
private fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Yellow_600,
) {

    val filledStars = floor(rating / 2).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.padding(5.dp)
            )
        }

        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.padding(5.dp)
            )
        }

        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}


@Preview
@Composable
fun RatingPreview() {
    RatingBar(rating = 9.3)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Test() {
    Header(
        showName = "Under the Dome",
        status = ShowStatus.ENDED,
        premiered = "2013-06-24",
        ended = "2015-09-10",
        schedule = Schedule("22:00", listOf("Thursday")),
        iconUrl = "iconURL"

    )
    Summary(
        showSummary = "Test",
        genres = listOf("Drama", "Science-Fiction", "Thriller"),
        rating = 6.8f
    )
}