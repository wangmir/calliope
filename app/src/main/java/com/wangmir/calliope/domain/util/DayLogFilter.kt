package com.wangmir.calliope.domain.util

import com.wangmir.calliope.domain.entities.EmotionLog

sealed class DayLogFilter(val orderType: OrderType) {
    class Year(orderType: OrderType, val year: Int): DayLogFilter(orderType)
    class Keyword(orderType: OrderType, val keyword: String): DayLogFilter(orderType)
    class Emotion(orderType: OrderType, val emotion: EmotionLog): DayLogFilter(orderType)
}
