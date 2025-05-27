package com.ab.sclr.domain.grid

import com.ab.sclr.domain.primitives.DimensionType
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Row(
    val id: String,
    val size: Float,
    // If using WEIGHT, this is the weight. If FIXED, this is Dp/absolute.
    val sizeType: DimensionType = DimensionType.WEIGHT
)