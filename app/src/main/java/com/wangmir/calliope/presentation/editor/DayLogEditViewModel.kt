package com.wangmir.calliope.presentation.editor

import androidx.lifecycle.ViewModel
import com.wangmir.calliope.domain.usecases.GetDayLog
import com.wangmir.calliope.domain.usecases.UpdateDayLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DayLogEditViewModel @Inject constructor(
    private val getDayLog: GetDayLog,
    private val updateDayLog: UpdateDayLog
): ViewModel() {
}
