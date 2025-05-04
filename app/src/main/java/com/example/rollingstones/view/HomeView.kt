package com.example.rollingstones.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rollingstones.ui.theme.BluLight
import com.example.rollingstones.ui.theme.MyPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BluLight),
        topBar = {
            TopAppBar(
                title = { Text(text = "Rolling Stones") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MyPurple
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "hello")
        }
    }
}
