package com.wangmir.calliope.adapters.data

import com.wangmir.calliope.domain.repositories.DataRepository
import com.wangmir.calliope.domain.entities.DayLog
import com.wangmir.calliope.domain.entities.EmotionLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.datetime.LocalDate

class DataRepositoryImpl(private val dayLogDao: DayLogDao) : DataRepository {

    override suspend fun insertDayLog(dayLog: DayLog) = dayLogDao.insert(dayLog)
    override suspend fun getDayLog(date: LocalDate): DayLog = dayLogDao.getDayLog(date)
    override suspend fun deleteDayLog(date: LocalDate) = dayLogDao.deleteDayLog(date)

    override fun getDayLogsByYear(year: Int): Flow<List<DayLog>> =
        dayLogDao.getDayLogsByYear(year).distinctUntilChanged()

    override fun getDayLogsByKeyword(keyword: String): Flow<List<DayLog>> =
        dayLogDao.getDayLogsByKeyword(keyword).distinctUntilChanged()

    override fun getDayLogsByEmotion(emotionLog: EmotionLog): Flow<List<DayLog>> =
        dayLogDao.getDayLogsByEmotion(emotionLog).distinctUntilChanged()
}
