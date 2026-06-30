package com.unsa.lab09.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unsa.lab09.viewmodel.BreedViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: BreedViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            BreedListScreen(
                viewModel = viewModel,
                onBreedClick = { breedName ->
                    navController.navigate("detail/$breedName")
                }
            )
        }

        composable(
            route = "detail/{breedName}",
            arguments = listOf(navArgument("breedName") { type = NavType.StringType })
        ) { backStackEntry ->
            val breedName = backStackEntry.arguments?.getString("breedName") ?: ""
            BreedDetailScreen(
                breedName = breedName,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}