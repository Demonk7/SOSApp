package com.example.sosapp
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

private val RedBlackColorScheme = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    surface = Color.Black,
    onSurface = Color.White,
    secondary = Color(0xFFbd3000),
    onSecondary = Color.Black,
)
data class HelpItem(
    val id: Int,
    val phoneNumber: String,
    val location: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpListScreen(items: List<HelpItem>,context:Context, navController: NavHostController) {
    val helpItems = remember { mutableStateOf<List<HelpItem>>(emptyList()) }
    MaterialTheme(colorScheme = RedBlackColorScheme) {
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
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                items.forEach { item ->
                    HelpCard(item = item, context = context)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun HelpCard(item: HelpItem, context: Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { openLocationInMaps(context, item.location) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Help ${item.id}",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Phone No.: ${item.phoneNumber}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Location: ${item.location}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.clickable {
                    openLocationInMaps(context, item.location)
                }
            )
        }
    }
}

fun openLocationInMaps(context: Context, location: String) {
    val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(location)}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    context.startActivity(mapIntent)
}


