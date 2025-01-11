package com.example.sosapp

import Emergency
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.sosapp.ui.theme.SOSAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SOSAppTheme {
                MainScreen()
            }
        }
    }
    @Composable
    fun MainScreen() {
        val context = LocalContext.current // Get the current context
        val items = listOf(
            HelpItem(id = 1, phoneNumber = "P1", location = "New York, USA"),
            HelpItem(id = 2, phoneNumber = "P2", location = "Los Angeles, USA"),
            HelpItem(id = 3, phoneNumber = "P3", location = "San Francisco, USA")
        )
        HelpList(items = items, context = context)
    }

}
