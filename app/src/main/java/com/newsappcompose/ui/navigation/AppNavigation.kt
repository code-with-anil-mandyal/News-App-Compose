package com.newsappcompose.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.newsappcompose.ui.screens.home.HomeScreen
import com.newsappcompose.ui.screens.SplashScreen
import com.newsappcompose.ui.screens.newsDetails.NewsDetailsScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
        startDestination = Screens.SPLASH.route,
        modifier = modifier,
    ){


        composable(Screens.SPLASH.route) {
            SplashScreen{
                navController.navigate(Screens.HOME.route){
                    popUpTo(Screens.SPLASH.route){inclusive = true}
                }
            }

        }

        composable(Screens.HOME.route) {
            HomeScreen(onItemClick = { source, sourceUrl ->
                val encodedSource = URLEncoder.encode(source, StandardCharsets.UTF_8.toString())
                val encodedSourceUrl = URLEncoder.encode(sourceUrl, StandardCharsets.UTF_8.toString())

                navController.navigate("${Screens.DETAILS.route}/$encodedSource/$encodedSourceUrl")
            })
        }

        composable(
            route = Screens.DETAILS.route + "/{source}/{sourceUrl}",
            arguments = listOf(
                navArgument("source") { type = NavType.StringType },
                navArgument("sourceUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val source = backStackEntry.arguments?.getString("source") ?: ""
            val sourceUrl = backStackEntry.arguments?.getString("sourceUrl") ?: ""
            NewsDetailsScreen(source, sourceUrl, onBackClick = {
                navController.popBackStack()
            })
        }



    }
}