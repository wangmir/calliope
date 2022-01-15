package com.wangmir.calliope.ui.view

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.wangmir.calliope.ui.components.BottomMenuContent
import com.wangmir.calliope.ui.components.BottomNavigator
import com.wangmir.calliope.ui.theme.CalliopeTheme

@ExperimentalMaterialApi
@Composable
fun Home() {
    CalliopeTheme {
        Scaffold(
            bottomBar = {
                BottomNavigator(
                    items = BottomMenuContent.values().toList(),
                    // todo: change this with regarding state
                    currentRoute = BottomMenuContent.LIST_VIEW.route,
                    navigateToRoute = {}
                )
            }
        ) {
            // todo: show navigated contents
        }
    }
}
