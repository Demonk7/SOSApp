package com.example.sosapp

import androidx.compose.foundation.background
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
import androidx.compose.material3.ButtonDefaults
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
}