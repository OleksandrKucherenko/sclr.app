package com.ab.sclr.domain

import com.ab.sclr.domain.background.Background
import com.ab.sclr.domain.layers.Layer
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Slide(
    val id: String, // Or UUID
    val layers: List<Layer> = listOf(),

    val widthRatio: Float = 1.0f,
    val background: Background = Background.empty()
)