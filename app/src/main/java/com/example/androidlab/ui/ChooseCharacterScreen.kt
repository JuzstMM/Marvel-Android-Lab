package com.example.androidlab.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
fun ChooseCharacterScreen(
    modifier: Modifier = Modifier,
    heroesList: List<MarvelCharacter>,
    onCharacterButtonClicked: (Int) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGray),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            modifier = Modifier.width(200.dp),
            painter = painterResource(R.drawable.marvelstudios),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.choose_hero),
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow {
            items(heroesList.size) { index ->
                val hero = heroesList[index]
                CharacterButton(hero, onClick = { onCharacterButtonClicked(hero.id) })
            }
        }

    }
}

@Composable
fun CharacterButton(
    hero: MarvelCharacter,
    onClick: () -> Unit
){
    Row {
        Spacer(modifier = Modifier.width(22.dp))
        Box(
            modifier = Modifier
                .width(350.dp)
                .height(500.dp)
                .clip(RoundedCornerShape(10))
                .clickable { onClick() }
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
            Text(
                text = stringResource(hero.name),
                color = Color.White,
                fontSize = 40.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(21.dp))
    }
}

@Preview
@Composable
fun ChooseCharacterScreenPreview(){
    AndroidLabTheme {
        ChooseCharacterScreen(heroesList = CharacterData.characters, onCharacterButtonClicked = {})
    }
}