package com.example.sosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Nav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "findHelpScreen"
    ) {
        composable("findHelpScreen") {
            FindHelpScreen(navController)
        }
        composable("detailsScreen/{helpText}") { backStackEntry ->
            val helpText = backStackEntry.arguments?.getString("helpText") ?: "No Details"
            DetailsScreen(helpText = helpText)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindHelpScreen(navController: NavHostController) {
    val helpList = remember {
        listOf(
            "Help received from John",
            "Help received from Sarah",
            "Help received from Alex",
            "Help received from Emma"
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Panic App", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black),
                modifier = Modifier.padding(0.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Modern "Find Help" Button
            Button(
                onClick = { /* TODO: Add action for 'Find Help' */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFbd3000)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Find Help",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // LazyColumn to display the list of help
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(helpList.size) { index ->
                    HelpItemCard(
                        helpText = helpList[index],
                        onClick = {
                            navController.navigate("detailsScreen/${helpList[index]}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HelpItemCard(helpText: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3C3C3C), RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            text = helpText,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(helpText: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = helpText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
