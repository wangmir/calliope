package com.wangmir.calliope.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.wangmir.calliope.domain.entities.Date
import com.wangmir.calliope.ui.components.NotYetImplementedView
import java.time.LocalDate

@Composable
@Destination(route = "home/day_log_view")
fun DayLogView(
    date: LocalDate
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NotYetImplementedView()
    }
}
