package com.wangmir.calliope.ui.view

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.wangmir.calliope.ui.components.BottomMenuContent
import com.wangmir.calliope.ui.components.BottomNavigator
import com.wangmir.calliope.ui.theme.CalliopeTheme

@ExperimentalMaterialApi
@Composable
fun Home() {
    val navController = rememberNavController()
    CalliopeTheme {
        DayLogListView(navController = navController)
    }
}
