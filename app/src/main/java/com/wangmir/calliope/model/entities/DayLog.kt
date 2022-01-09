package com.wangmir.calliope.model.entities

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
)

data class Date (
    val year: Int,
    val month: Int,
    val dayOfMonth: Int
) {
    constructor(date: LocalDate) : this(date.year, date.monthNumber, date.dayOfMonth)
}
