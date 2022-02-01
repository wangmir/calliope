package com.wangmir.calliope.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wangmir.calliope.domain.entities.Date
import com.wangmir.calliope.presentation.editor.DayLogEditViewModel
import com.wangmir.calliope.ui.components.NotYetImplementedView
import java.time.LocalDate

@Composable
@Destination(route = "home/day_log_view")
fun DayLogView(
    navigator: DestinationsNavigator,
    date: LocalDate,
    viewModel: DayLogEditViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NotYetImplementedView()
    }
}
