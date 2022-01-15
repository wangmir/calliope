package com.wangmir.calliope.model.entities

data class TextLog(
    val text: String
) {
    fun asSingleLine() = text.lines().joinToString(separator = " ")
}
