package com.wangmir.calliope.domain.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity
data class DayLog (
    @PrimaryKey
    @Embedded(prefix = "date_")
    val date: Date,
    @Embedded(prefix = "voiceLog_")
    val voiceLog: VoiceLog?,
    @Embedded(prefix = "textLog_")
    val textLog: TextLog = TextLog(""),
    @Embedded(prefix = "sttLog_")
    val sttLog: SttLog?,
    val emotionLog: EmotionLog = EmotionLog.NotSet
) {

    companion object {
        fun getMockDayLog() = DayLog(
                Date(2022, 1, 12),
                voiceLog = null,
                textLog = TextLog("""
                    오늘은 오마카세를 먹고 왔다. 맛있었다. 또 먹고 싶다. 
                    요즘 요가를 하면서 내면을 바라보는 일을 집중해보려고 하는데, 내면은 개뿔, 먹는게 최고다.
                    내일은 버거킹 먹어야지.
                """.trimIndent()),
                sttLog = null,
                emotionLog = EmotionLog.Joyful
            )
    }
}

data class Date (
    val year: Int,
    val month: Int,
    val dayOfMonth: Int
) {
    constructor(date: LocalDate) : this(date.year, date.monthNumber, date.dayOfMonth)

    override fun toString(): String {
        return "$year.$month.$dayOfMonth."
    }

    fun toInt(): Int {
        return "$year$month$dayOfMonth".toInt()
    }
}
