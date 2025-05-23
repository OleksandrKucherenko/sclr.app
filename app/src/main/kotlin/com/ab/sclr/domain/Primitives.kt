package com.ab.sclr.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SclrPoint(val x: Float, val y: Float)

@JsonClass(generateAdapter = true)
data class SclrSize(val width: Float, val height: Float)

@JsonClass(generateAdapter = true)
data class SclrRect(val x: Float, val y: Float, val width: Float, val height: Float)

@JsonClass(generateAdapter = true)
data class SclrPadding(
    val top: Float = 0f,
    val bottom: Float = 0f,
    val left: Float = 0f,
    val right: Float = 0f
) {
    constructor(all: Float) : this(all, all, all, all)
    constructor(horizontal: Float, vertical: Float) : this(
        vertical,
        vertical,
        horizontal,
        horizontal
    )
}
