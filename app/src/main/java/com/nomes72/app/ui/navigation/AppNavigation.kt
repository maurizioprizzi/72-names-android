package com.nomes72.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nomes72.app.ui.screen.DetailScreen
import com.nomes72.app.ui.screen.HomeScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNameClick = { number ->
                    navController.navigate("detail/$number")
                }
            )
        }

        composable(
            route = "detail/{nameNumber}",
            arguments = listOf(
                navArgument("nameNumber") { type = NavType.IntType }
            )
        ) {
            DetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}