package com.example.sosapp

import Emergency
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

sealed class Screen(val route: String) {
    object Emergency : Screen("emergency")
    object FindHelp : Screen("findHelp")
    object FindingHelp:Screen("findingHelp")
    object HelpList : Screen("helpList")
    object NoHelpFound : Screen("noHelpFound")
    object Details : Screen("details/{helpText}")
    object Login : Screen("login")
    object Signup: Screen("signup")
    {
        fun createRoute(helpText: String) = "details/$helpText"
    }
}

@Composable
fun MainNavigation(context: Context) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable(Screen.Login.route) {
            LoginPage(navController)        //1st Page
        }
        composable(Screen.Signup.route) {
            SignUpPage(navController)           //2nd Page
        }
        composable(Screen.FindHelp.route) {
            FindHelpScreen(navController)       //3rd page
        }

        composable(Screen.Emergency.route) {
            Emergency(navController)            //4th Page
        }

        composable(Screen.FindingHelp.route) {
            FindingHelpScreen(navController)    //5th Page
        }


        composable(Screen.HelpList.route) {
            // Create mock help items for demonstration
            val helpItems = listOf(
                HelpItem(1, "123-456-7890", "San Francisco, CA"),
                HelpItem(2, "234-567-8901", "Oakland, CA"),
                HelpItem(3, "345-678-9012", "Berkeley, CA")
            )
            HelpListScreen(items = helpItems, context = context, navController = navController)
        }       //6th Page

        composable(Screen.NoHelpFound.route) {
            NoHelpFoundScreen(navController)
        }       //6th page

        composable(Screen.Details.route) { backStackEntry ->
            val helpText = backStackEntry.arguments?.getString("helpText") ?: "No Details"
            DetailsScreen(helpText = helpText, navController = navController)
        }
    }
}
