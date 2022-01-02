package com.wangmir.calliope.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class DayLog(
    @PrimaryKey
    val date: Date,
    val voiceLog: VoiceLog?,
    val textLog: TextLog?,
    val sttLog: SttLog?,
    val emotionLog: EmotionLog = EmotionLog.NotSet
)
