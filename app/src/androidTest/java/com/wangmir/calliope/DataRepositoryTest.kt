package com.wangmir.calliope

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.wangmir.calliope.domain.repositories.DataRepository
import com.wangmir.calliope.adapters.data.DataRepositoryImpl
import com.wangmir.calliope.adapters.data.LocalDatabase
import com.wangmir.calliope.domain.entities.Date
import com.wangmir.calliope.domain.entities.DayLog
import com.wangmir.calliope.domain.entities.EmotionLog
import com.wangmir.calliope.domain.entities.TextLog
import com.wangmir.calliope.testutils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.*

@ExperimentalCoroutinesApi
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DataRepositoryTest {

    companion object {
        private val context: Context = ApplicationProvider.getApplicationContext()
        private val repository: DataRepository =
            DataRepositoryImpl(LocalDatabase.getInMemoryDatabase(context).dayLogDao)
    }

    private val defaultDayLog
        get() = DayLog(
            Date(2021, 10, 21),
            voiceLog = null,
            textLog = TextLog("Test"),
            sttLog = null,
            EmotionLog.Happy
        )

    @Test
    @Order(1)
    fun testDayLogInsert() = runBlocking {
        val dayLog = defaultDayLog

        assertDoesNotThrow {
            repository.insertDayLog(dayLog)
        }
    }

    @Test
    @Order(2)
    fun testDayLogGet() = runBlocking {
        val result = repository.getDayLog(LocalDate(2021, 10, 21))
        assertThat(result.textLog.text).isEqualTo("Test")
    }

    @Test
    @Order(3)
    fun testDayLogDelete() = runBlocking {
        val date = LocalDate(2021, 10, 21)
        val beforeResult = repository.getDayLog(date)
        assertThat(beforeResult).isNotNull()
        repository.deleteDayLog(date)
        val afterResult = repository.getDayLog(date)
        assertThat(afterResult).isNull()
    }

    @Test
    @Order(4)
    fun testUpdateDayLog() = runBlocking {
        val date = LocalDate(2021, 10, 21)
        val dayLog = defaultDayLog

        repository.insertDayLog(dayLog)
        val beforeResult = repository.getDayLog(date)
        assertThat(beforeResult.emotionLog).isEqualTo(EmotionLog.Happy)

        val dayLogUpdate = dayLog.copy(emotionLog = EmotionLog.Sad)
        repository.insertDayLog(dayLogUpdate)
        val afterResult = repository.getDayLog(date)
        assertThat(afterResult.emotionLog).isEqualTo(EmotionLog.Sad)
    }

    @Test
    @Order(5)
    fun testGetDayLogByYear() = runBlocking {

        repository.insertDayLog(defaultDayLog)
        repository.insertDayLog(defaultDayLog.copy(date = Date(2021, 10, 22)))
        repository.insertDayLog(defaultDayLog.copy(date = Date(2021, 10, 23)))
        repository.insertDayLog(defaultDayLog.copy(date = Date(2021, 10, 24)))
        repository.insertDayLog(defaultDayLog.copy(date = Date(2022, 10, 22)))
        repository.insertDayLog(defaultDayLog.copy(date = Date(2022, 10, 23)))
        repository.insertDayLog(defaultDayLog.copy(date = Date(2022, 10, 24)))

        var dayLogList = repository
            .getDayLogsByYear(2021)
            .getOrAwaitValue(this, dispatcher = Dispatchers.IO)

        assertThat(dayLogList).isNotNull()
        assertThat(dayLogList.size).isEqualTo(4)
        assertThat(dayLogList::find{ it.date.dayOfMonth == 23 }!!.emotionLog).isEqualTo(EmotionLog.Happy)
    }

    @Test
    @Order(6)
    fun testGetDayLogByKeyword() = runBlocking {

        repository.insertDayLog(defaultDayLog)
        repository.insertDayLog(
            defaultDayLog.copy(
                date = Date(2021, 10, 22),
                textLog = TextLog("keyword handler")
            )
        )
        repository.insertDayLog(
            defaultDayLog.copy(
                date = Date(2021, 10, 23),
                textLog = TextLog("keyword sword")
            )
        )
        repository.insertDayLog(
            defaultDayLog.copy(
                date = Date(2021, 10, 24),
                textLog = TextLog("keyring sword")
            )
        )
        repository.insertDayLog(defaultDayLog.copy(date = Date(2022, 10, 22)))
        repository.insertDayLog(defaultDayLog.copy(date = Date(2022, 10, 23)))
        repository.insertDayLog(defaultDayLog.copy(date = Date(2022, 10, 24)))

        var dayLogList = repository
            .getDayLogsByKeyword("keyword")
            .getOrAwaitValue(this, dispatcher = Dispatchers.IO)

        assertThat(dayLogList).isNotNull()
        assertThat(dayLogList.size).isEqualTo(2)
        assertThat(dayLogList::find{ it.textLog.text == "keyword sword" }).isNotNull()
    }

    @Test
    @Order(7)
    fun testGetDayLogByEmotion() = runBlocking {

        repository.insertDayLog(defaultDayLog)
        repository.insertDayLog(
            defaultDayLog.copy(
                date = Date(2021, 10, 22),
                emotionLog = EmotionLog.Desirous
            )
        )
        repository.insertDayLog(
            defaultDayLog.copy(
                date = Date(2021, 10, 23),
                emotionLog = EmotionLog.Joyful
            )
        )
        repository.insertDayLog(
            defaultDayLog.copy(
                date = Date(2021, 10, 24),
                emotionLog = EmotionLog.Joyful
            )
        )
        repository.insertDayLog(defaultDayLog.copy(date = Date(2022, 10, 22)))
        repository.insertDayLog(defaultDayLog.copy(date = Date(2022, 10, 23)))
        repository.insertDayLog(defaultDayLog.copy(date = Date(2022, 10, 24)))

        var dayLogList = repository
            .getDayLogsByEmotion(EmotionLog.Joyful)
            .getOrAwaitValue(this, dispatcher = Dispatchers.IO)

        assertThat(dayLogList).isNotNull()
        assertThat(dayLogList.size).isEqualTo(2)
        assertThat(dayLogList.last().date.dayOfMonth).isEqualTo(24)
    }
}
