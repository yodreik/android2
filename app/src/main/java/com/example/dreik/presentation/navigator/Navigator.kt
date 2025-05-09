package com.example.dreik.presentation.navigator

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dreik.R
import com.example.dreik.presentation.home.HomeScreen
import com.example.dreik.presentation.home.HomeViewModel
import com.example.dreik.presentation.profile.ProfileScreen
import com.example.dreik.presentation.profile.ProfileViewModel
import com.example.dreik.presentation.workout.WorkoutScreen
import com.example.dreik.presentation.workout.WorkoutViewModel
import com.example.dreik.presentation.history.HistoryScreen
import com.example.dreik.presentation.history.HistoryViewModel
import com.example.dreik.presentation.navgraph.Route
import com.example.dreik.presentation.navigator.components.NavigationItem
import com.example.dreik.presentation.navigator.components.BottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigator() {

    val bottomNavigationItems = remember {
        listOf(
            NavigationItem(icon = R.drawable.ic_home),
            NavigationItem(icon = R.drawable.ic_person),
            NavigationItem(icon = R.drawable.ic_add),
            NavigationItem(icon = R.drawable.ic_journal),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.ProfileScreen.route -> 1
        Route.WorkoutScreen.route -> 2
        Route.HistoryScreen.route -> 3
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.ProfileScreen.route ||
                backStackState?.destination?.route == Route.WorkoutScreen.route ||
                backStackState?.destination?.route == Route.HistoryScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            BottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.ProfileScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.WorkoutScreen.route
                        )

                        3 -> navigateToTab(
                            navController = navController,
                            route = Route.HistoryScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                HomeScreen(

                )
            }
            composable(route = Route.ProfileScreen.route) { backStackEntry ->
                ProfileScreen(

                )
            }
            composable(route = Route.WorkoutScreen.route) { backStackEntry ->
                WorkoutScreen(

                )
            }
            composable(route = Route.HistoryScreen.route) { backStackEntry ->
                HistoryScreen(

                )
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}