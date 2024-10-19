package com.borjadelgadodev.cervezaymasapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.borjadelgadodev.cervezaymasapp.ui.screens.detail.DetailScreen
import com.borjadelgadodev.cervezaymasapp.ui.screens.home.HomeScreen
import com.borjadelgadodev.cervezaymasapp.ui.screens.home.HomeViewModel
import com.borjadelgadodev.cervezaymasapp.ui.screens.detail.DetailViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel()

    homeViewModel.onUiReady()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onClick = { brewery ->
                navController.navigate("detail/${brewery.id}")
            })
        }
        composable(
            "detail/{breweryId}",
            arguments = listOf(navArgument("breweryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val breweryId = requireNotNull(backStackEntry.arguments?.getString("breweryId"))
            DetailScreen(
                DetailViewModel(breweryId),
                onBack = { navController.popBackStack() }
            )
        }
    }
}