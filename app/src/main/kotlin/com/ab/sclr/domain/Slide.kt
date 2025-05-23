package com.ab.sclr.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Slide(
    val id: String, // Or UUID
    val layers: List<Layer>,

    val widthRatio: Float = 1.0f,
    val background: Background? = null
)