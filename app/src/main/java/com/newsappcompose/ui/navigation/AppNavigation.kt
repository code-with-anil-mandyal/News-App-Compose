package com.newsappcompose.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.newsappcompose.ui.screens.HomeScreen
import com.newsappcompose.ui.screens.SplashScreen

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }

}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier= Modifier
){
    NavHost(
        navController = navController,
        startDestination = Screens.SPLASH.rout,
        modifier = modifier,
    ){


        composable(Screens.SPLASH.rout) {
            SplashScreen{
                navController.navigate(Screens.HOME.rout){
                    popUpTo(Screens.SPLASH.rout){inclusive = true}
                }
            }

        }

        composable(Screens.HOME.rout){
            HomeScreen()
        }



    }
}