package com.wangmir.calliope.model

import com.wangmir.calliope.model.entities.DayLog
import com.wangmir.calliope.model.entities.EmotionLog
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface DataRepository {
    suspend fun insertDayLog(dayLog: DayLog)
    suspend fun getDayLog(date: LocalDate): DayLog
    suspend fun deleteDayLog(date: LocalDate)

    fun getDayLogsByYear(year: Int): Flow<List<DayLog>>
    fun getDayLogsByKeyword(keyword: String): Flow<List<DayLog>>
    fun getDayLogsByEmotion(emotionLog: EmotionLog): Flow<List<DayLog>>
}
