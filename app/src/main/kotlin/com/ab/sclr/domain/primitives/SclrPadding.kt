package com.ab.sclr.domain.primitives

import com.squareup.moshi.JsonClass

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
