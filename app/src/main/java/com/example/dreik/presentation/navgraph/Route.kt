package com.example.dreik.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object HomeScreen : Route(route = "homeScreen")

    object ProfileScreen : Route(route = "profileScreen")

    object WorkoutScreen : Route(route = "workoutScreen")

    object HistoryScreen : Route(route = "historyScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object Navigation : Route(route = "navigation")

    object NavigatorScreen : Route(route = "navigator")
}