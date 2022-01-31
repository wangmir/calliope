package com.wangmir.calliope.domain.usecases

import com.wangmir.calliope.domain.entities.DayLog
import com.wangmir.calliope.domain.repositories.DataRepository
import com.wangmir.calliope.domain.util.DayLogFilter
import com.wangmir.calliope.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDayLogList(
    private val dataRepository: DataRepository
) {
    operator fun invoke(filter: DayLogFilter): Flow<List<DayLog>> {
        val logs = when (filter) {
            is DayLogFilter.Year -> dataRepository.getDayLogsByYear(filter.year)
            is DayLogFilter.Keyword -> dataRepository.getDayLogsByKeyword(filter.keyword)
            is DayLogFilter.Emotion -> dataRepository.getDayLogsByEmotion(filter.emotion)
        }
        return logs.map { dayLogs ->
            when (filter.orderType) {
                is OrderType.Ascending -> dayLogs.sortedBy { it.date.toInt() }
                is OrderType.Descending -> dayLogs.sortedByDescending { it.date.toInt() }
            }
        }
    }
}
