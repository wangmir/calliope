package com.wangmir.calliope.presentation.list

import com.wangmir.calliope.domain.entities.DayLog
import com.wangmir.calliope.domain.util.DayLogFilter

sealed class DayLogListEvent {
    data class Filter(val dayLogFilter: DayLogFilter): DayLogListEvent()
    data class DeleteNote(val dayLog: DayLog): DayLogListEvent()
    object RestoreNote: DayLogListEvent()
    object ToggleOrderSection: DayLogListEvent()
}
