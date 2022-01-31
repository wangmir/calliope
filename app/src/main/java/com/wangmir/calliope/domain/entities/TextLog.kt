package com.wangmir.calliope.domain.entities

data class TextLog(
    val text: String
) {
    fun asSingleLine() = text.lines().joinToString(separator = " ")
}
