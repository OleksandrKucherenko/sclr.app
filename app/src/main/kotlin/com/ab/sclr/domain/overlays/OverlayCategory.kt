package com.ab.sclr.domain.overlays

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OverlayCategory(
    val title: String,
    val id: Number,
    @Json(name = "thumbnail_url") val thumbnailUrl: String,
    val items: List<Overlay>
)