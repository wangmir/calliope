package com.wangmir.calliope.ui.view

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.wangmir.calliope.ui.components.BottomMenuContent
import com.wangmir.calliope.ui.components.BottomNavigator
import com.wangmir.calliope.ui.theme.CalliopeTheme
import com.wangmir.calliope.ui.view.destinations.DayLogListViewDestination

@ExperimentalMaterialApi
@Composable
fun Home() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination
    val scaffoldState = rememberScaffoldState()
//    val enabled = currentScreen!! == DayLogListViewDestination

    CalliopeTheme {
        Scaffold(
            bottomBar = {
                BottomNavigator(
                    items = BottomMenuContent.values().toList(),
                    // todo: change this with regarding state
                    currentRoute = currentScreen?.route ?: BottomMenuContent.LIST_VIEW.route,
                    navigateToRoute = navController::navigate
                )
            },
            floatingActionButton = {
                if (currentScreen?.route == DayLogListViewDestination.route) FloatingActionButton(
                    onClick = {
                        // todo: Make add view
                        Log.d(TAG, "Add DayLog clicked")
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add DayLog")
                }
            },
            scaffoldState = scaffoldState
        ) {
            DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
        }
    }
}
