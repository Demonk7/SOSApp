package com.example.sosapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "findingHelpScreen"
    ) {
        composable("findingHelpScreen") {
            FindingHelpScreen(navController = navController)
        }
        composable("helpListScreen") {
            HelpListScreen(navController = navController)
        }
    }
}
