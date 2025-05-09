package com.example.dreik.presentation.navgraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.dreik.presentation.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
        composable(route = Route.HomeScreen.route) {
            HomeScreen()
        }
    }
}

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.HomeScreen.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route){
                HomeScreen()
            }
        }
    }
}