package com.example.rollingstones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.rollingstones.naviigation.NavigationGraph
import com.example.rollingstones.ui.theme.RollingStonesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            RollingStonesTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(Color.Black)
                ) {
                    NavigationGraph(navController)
                }
            }
        }
    }
}

