package com.ab.sclr.domain.primitives

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SclrRect(val x: Float, val y: Float, val width: Float, val height: Float)