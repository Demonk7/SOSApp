package com.example.sosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.weight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

class NoHelpFound : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoHelpFoundScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoHelpFoundScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Panic App", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF0000000)), // Matching Emergency screen button color
                modifier = Modifier.padding(0.dp) // Ensuring no padding around the AppBar
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color(0xFFFB6B30), Color(0xFFbd3000)))) // Gradient colors matching Emergency screen
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White, shape = RoundedCornerShape(24.dp))
                    .padding(24.dp)
            ) {
                // Icon Section
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFFFB6B30), shape = RoundedCornerShape(50)) // Color matching the emergency button
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "!",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Title Section
                Text(
                    text = "No Help Found",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Description
                Text(
                    text = "It seems we couldn't find the help you're looking for. Please try again or go back to the previous screen.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Buttons with icons and space, making them align in extreme corners
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,  // Buttons will be spaced in the extreme corners
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),  // Add horizontal padding for better spacing
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ModernButton(
                        text = "Try Again",
                        backgroundColor = Color(0xFFbd3000),  // Matching button color from Emergency screen
                        textColor = Color.White,
                        icon = Icons.Filled.Refresh,  // Refresh icon for Try Again
                        onClick = { /* Handle Try Again logic */ }
                    )
                    ModernButton(
                        text = "Go Back",
                        backgroundColor = Color(0xFF8C8C8C),  // Matching Go Back button color (gray)
                        textColor = Color.White,
                        icon = Icons.Filled.ArrowBack,  // ArrowBack icon for Go Back
                        onClick = { /* Handle Go Back logic */ }
                    )
                }
            }
        }
    }
}

@Composable
fun ModernButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            //.weight(1f)// Give each button equal weight
            .clickable { onClick() }
            .background(backgroundColor, shape = RoundedCornerShape(16.dp)),  // Rectangular shape with rounded corners
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(1.dp))  // Space between icon and text
            Text(
                text = text,
                fontSize = 16.sp,
                color = textColor,
                fontWeight = FontWeight.Bold,
                maxLines = 1,  // Ensures that the text does not wrap to multiple lines
                //overflow = TextOverflow.Ellipsis  // If the text overflows, it will display an ellipsis
            )
        }
    }
}
