package com.wangmir.calliope.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
