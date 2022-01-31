package com.wangmir.calliope.presentation.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.wangmir.calliope.domain.usecases.GetDayLogList
import com.wangmir.calliope.domain.util.DayLogFilter
import com.wangmir.calliope.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DayLogListViewModel @Inject constructor(
    private val getDayLogList: GetDayLogList
) : ViewModel() {
    val dayLogList get() = getDayLogList(_filter.value)
    val filter get() = _filter
    private val _filter = mutableStateOf<DayLogFilter>(
        DayLogFilter.Year(OrderType.Ascending, LocalDate.now().year)
    )

    fun onEvent(event: DayLogListEvent) {
        when (event) {
            is DayLogListEvent.Filter -> {
                _filter.value = event.dayLogFilter
            }
            else -> {
                throw NotImplementedError("$event")
            }
        }
    }
}
