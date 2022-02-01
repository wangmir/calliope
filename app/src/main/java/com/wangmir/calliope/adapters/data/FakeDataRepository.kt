package com.wangmir.calliope.adapters.data

import com.wangmir.calliope.domain.entities.Date
import com.wangmir.calliope.domain.entities.DayLog
import com.wangmir.calliope.domain.entities.EmotionLog
import com.wangmir.calliope.domain.entities.TextLog
import com.wangmir.calliope.domain.repositories.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.LocalDate

class FakeDataRepository : DataRepository {

    companion object {
        const val MOCK_KEYWORD = "MOCK_KEYWORD"
    }

    val list = mutableListOf<DayLog>()
    val mockData = DayLog.getMockDayLog()

    init {
        // year 2021
        list.add(mockData.copy(date = Date(2021, 10, 30)))
        list.add(mockData.copy(date = Date(2021, 9, 28)))

        // year 2022
        list.add(mockData.copy(date = Date(2022, 1, 21)))
        list.add(mockData.copy(date = Date(2022, 1, 28)))

        // keyword has "MOCK_KEYWORD"
        list.add(mockData.copy(textLog = TextLog(MOCK_KEYWORD)))
        list.add(mockData.copy(textLog = TextLog(MOCK_KEYWORD + "tail misc text")))

        // emotion is Fear
        list.add(mockData.copy(emotionLog = EmotionLog.Fear))
    }

    override suspend fun insertDayLog(dayLog: DayLog) {
        list.add(dayLog)
    }

    override suspend fun getDayLog(date: LocalDate): DayLog? {
        return list.find { it.date == Date(date) }
    }

    override suspend fun deleteDayLog(date: LocalDate) {
        list.removeIf { it.date == Date(date) }
    }

    override fun getDayLogsByYear(year: Int): Flow<List<DayLog>> {
        return flowOf(list.filter { it.date.year == year })
    }

    override fun getDayLogsByKeyword(keyword: String): Flow<List<DayLog>> {
        return flowOf(list.filter { it.textLog.text.contains(keyword) })
    }

    override fun getDayLogsByEmotion(emotionLog: EmotionLog): Flow<List<DayLog>> {
        return flowOf(list.filter { it.emotionLog == emotionLog })
    }
}
