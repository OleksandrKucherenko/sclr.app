package com.ab.sclr.domain.primitives

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SclrSize(val width: Float, val height: Float)