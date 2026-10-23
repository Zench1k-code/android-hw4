package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currentArtwork by remember { mutableStateOf(1) }

    val imageRes = when (currentArtwork) {
        1 -> R.drawable.art_1
        2 -> R.drawable.art_2
        3 -> R.drawable.art_3
        else -> R.drawable.art_4
    }
    val title = when (currentArtwork) {
        1 -> R.string.artwork_title_1
        2 -> R.string.artwork_title_2
        3 -> R.string.artwork_title_3
        else -> R.string.artwork_title_4
    }
    val artist = when (currentArtwork) {
        1 -> R.string.artwork_artist_1
        2-> R.string.artwork_artist_2
        3 -> R.string.artwork_artist_3
        else -> R.string.artwork_artist_4
    }
    val year = when (currentArtwork) {
        1 -> R.string.artwork_year_1
        2 -> R.string.artwork_year_2
        3 -> R.string.artwork_year_3
        else -> R.string.artwork_year_4
    }


    Column(
        modifier = modifier
            .safeDrawingPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ArtworkWall(imageRes = imageRes)
        ArtDescriptor(title = title, artist = artist, year = year)
        DisplayControl(
            onPreviewClick = { },
            onNextClick = {
                currentArtwork = when (currentArtwork) {
                    1 -> 2
                    2 -> 3
                    3 -> 4
                    else -> 1
                }
            }
        )
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier){
    Image(
        painter = painterResource(imageRes),
        contentDescription = stringResource(R.string.artwork_description),
        modifier = modifier.padding(16.dp)
    )
}

@Composable
fun ArtDescriptor(
    @StringRes title: Int,
    @StringRes artist: Int,
    @StringRes year: Int,
    modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = stringResource(artist) + " (" +
                    stringResource(year) + ")",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun DisplayControl(
    onPreviewClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onPreviewClick) {
            Text(stringResource(R.string.previous))
        }
        Button(onClick = onNextClick) {
            Text(stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}