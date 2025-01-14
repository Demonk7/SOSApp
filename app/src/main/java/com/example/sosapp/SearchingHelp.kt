package com.example.sosapp

import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun FindingHelpScreen(navController:NavHostController) {//onSearchComplete: (List<User>) -> Unit
    val isSearching = remember { mutableStateOf(true) }
    val helpItems=remember {mutableStateOf<List<HelpItem>>(emptyList())}
    // Simulate the search process
    LaunchedEffect(Unit) {
        delay(3000) // Simulate a 3-second loading delay
        val usersNearby = findUsersNearby(currentLocation = getCurrentLocation(), radiusInKm = 10.0)
        isSearching.value = false
        //onSearchComplete(usersNearby)
        if (usersNearby.isNotEmpty()) {
            helpItems.value=usersNearby.mapIndexed({index,user->
                HelpItem(
                    id=index+1,
                    phoneNumber = "123-456-789",
                    location="${user.location.latitude}, ${user.location.longitude}"
            })
            navController.navigate("helpListScreen")
        }
        else {
            println("No helpers found nearby.")
        }
    }
    if (isSearching.value) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Finding Help...", fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

// Mock location object
fun getCurrentLocation(): Location {
    val location = Location("mock")
    location.latitude = 37.7749 // Example latitude
    location.longitude = -122.4194 // Example longitude
    return location
}

// User data class
data class User(val name: String, val location: Location)

// Function to find users nearby
fun findUsersNearby(currentLocation: Location, radiusInKm: Double): List<User> {
    // Mock data: List of registered users with their locations
    val registeredUsers = listOf(
        User("Alice", Location("mock").apply {
            latitude = 37.7749
            longitude = -122.4194
        }),
        User("Bob", Location("mock").apply {
            latitude = 37.7849
            longitude = -122.4094
        }),
        User("Charlie", Location("mock").apply {
            latitude = 37.8049
            longitude = -122.4294
        })
    )

    // Filter users within the radius
    return registeredUsers.filter { user ->
        val distanceInMeters = FloatArray(1)
        Location.distanceBetween(
            currentLocation.latitude,
            currentLocation.longitude,
            user.location.latitude,
            user.location.longitude,
            distanceInMeters
        )
        val distanceInKm = distanceInMeters[0] / 1000.0
        distanceInKm <= radiusInKm
    }
}
