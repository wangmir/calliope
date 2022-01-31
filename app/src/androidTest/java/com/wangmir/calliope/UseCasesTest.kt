package com.wangmir.calliope

import com.google.common.truth.Truth.assertThat
import com.wangmir.calliope.adapters.data.FakeDataRepository
import com.wangmir.calliope.domain.usecases.GetDayLogList
import com.wangmir.calliope.domain.util.DayLogFilter
import com.wangmir.calliope.domain.util.OrderType
import com.wangmir.calliope.testutils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class UseCasesTest {

    private val getDayLogList get() = GetDayLogList(FakeDataRepository())

    @Test
    fun testGetDayLogListByYear() = runBlocking {
        val uc = getDayLogList
        var filter = DayLogFilter.Year(OrderType.Ascending, 2021)
        var result = uc(filter).getOrAwaitValue(this, dispatcher = Dispatchers.IO)
        assertThat(result).isNotNull()
        result.forEach {
            assertThat(it.date.year).isEqualTo(2021)
        }

        assertThat(result[0].date.toInt()).isLessThan(result[1].date.toInt())

        filter = DayLogFilter.Year(OrderType.Descending, 2021)
        result = uc(filter).getOrAwaitValue(this, dispatcher = Dispatchers.IO)
        result.forEach {
            assertThat(it.date.year).isEqualTo(2021)
        }

        assertThat(result[0].date.toInt()).isGreaterThan(result[1].date.toInt())
    }
}
