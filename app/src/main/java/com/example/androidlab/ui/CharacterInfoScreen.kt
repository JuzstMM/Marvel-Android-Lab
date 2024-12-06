package com.example.androidlab.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.androidlab.R
import com.example.androidlab.data.CharacterData
import com.example.androidlab.data.MarvelCharacter
import com.example.androidlab.ui.theme.AndroidLabTheme
import com.example.androidlab.ui.theme.BackgroundGray

@Composable
fun CharacterInfoScreen(
    modifier: Modifier = Modifier,
    hero: MarvelCharacter,
    onBackButtonClicked: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(hero.img)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        BackButton(onClick = {onBackButtonClicked()})
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)) {
            Text(
                text = stringResource(hero.name),
                fontSize = 30.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(hero.description),
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = { onClick() } ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "Back",
            modifier = Modifier.size(32.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@Preview
@Composable
fun CharacterInfoScreenPreview() {
    AndroidLabTheme {
        CharacterInfoScreen(hero = CharacterData.characters[0], onBackButtonClicked = {})
    }
}

