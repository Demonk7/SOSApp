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
fun MainNavigation() {
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
                title = { Text("Find Help", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Button(
                onClick = { /* TODO: Add action for 'Find Help' */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Find Help",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(helpList.size) { index ->
                    HelpItemCard(
                        helpText = helpList[index],
                        onClick = {
                            // Navigate to the details screen
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
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            text = helpText,
            fontSize = 16.sp,
            color = Color.Black,
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
                .background(Color.White)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = helpText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}


/*import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FindHelp(){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                ) {
                Text(text = "Find Help",
                    color = Color.White,
                    fontSize = 18.sp)
            }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
             LazyColumn (){  }
        }
    }
}*/