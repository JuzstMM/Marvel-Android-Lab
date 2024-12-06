package com.example.androidlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidlab.ui.theme.AndroidLabTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidlab.data.CharacterData
import com.example.androidlab.ui.CharacterInfoScreen
import com.example.androidlab.ui.ChooseCharacterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  
        enableEdgeToEdge()
        setContent {
            AndroidLabTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MarvelApp()
                }
            }
        }
    }
}

enum class HeroApp{
    Menu,
    Hero
}

@Composable
fun MarvelApp(navController: NavHostController = rememberNavController())
{
    var heroID = 1
    NavHost(
        navController = navController,
        startDestination = HeroApp.Menu.name
    ){
        composable(route = HeroApp.Menu.name)
        {
            ChooseCharacterScreen(
                heroesList = CharacterData.characters,
                onCharacterButtonClicked = {
                    heroID = it
                    navController.navigate(HeroApp.Hero.name)
                }
            )
        }
        composable(route = HeroApp.Hero.name)
        {
            CharacterInfoScreen(
                hero = CharacterData.characters[heroID-1],
                onBackButtonClicked = {navController.popBackStack()}
            )
        }
    }
}