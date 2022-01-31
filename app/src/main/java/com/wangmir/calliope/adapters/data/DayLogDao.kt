package com.wangmir.calliope.adapters.data

import androidx.room.Dao
import androidx.room.Query
import com.wangmir.calliope.domain.entities.Date
import com.wangmir.calliope.domain.entities.DayLog
import com.wangmir.calliope.domain.entities.EmotionLog
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

@Dao
abstract class DayLogDao: BaseDao<DayLog>() {
    @Query("SELECT * FROM DayLog WHERE date_year = :year AND date_month = :month AND date_dayOfMonth = :dayOfMonth")
    abstract suspend fun getDayLog(year: Int, month: Int, dayOfMonth: Int): DayLog

    @Query("SELECT * FROM DayLog WHERE date_year = :year")
    abstract fun getDayLogsByYear(year: Int): Flow<List<DayLog>>
    @Query("SELECT * FROM DayLog WHERE textLog_text LIKE '%'|| :keyword ||'%'")
    abstract fun getDayLogsByKeyword(keyword: String): Flow<List<DayLog>>
    @Query("SELECT * FROM DayLog WHERE emotionLog = :emotionLog")
    abstract fun getDayLogsByEmotion(emotionLog: EmotionLog): Flow<List<DayLog>>

    suspend fun getDayLog(date: LocalDate): DayLog {
        return getDayLog(date.year, date.monthNumber, date.dayOfMonth)
    }

    fun deleteDayLog(date: LocalDate) {
        return delete(DayLog(Date(date), voiceLog = null, sttLog = null))
    }
}
