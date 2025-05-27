package com.ab.sclr.domain.primitives

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SclrPoint(val x: Float, val y: Float)