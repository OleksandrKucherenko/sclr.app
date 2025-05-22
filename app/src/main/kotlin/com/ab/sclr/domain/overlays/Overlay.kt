package com.ab.sclr.domain.overlays

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Overlay(
    val id: Number,
    @Json(name = "overlay_name") val overlayName: String,
    @Json(name = "created_at") val createdAt: Number,
    @Json(name = "category_id") val categoryId: Number,
    @Json(name = "source_url") val sourceUrl: String,
    @Json(name = "is_premium") val isPremium: Boolean,
    @Json(name = "includes_scrl_branding") val includesScrlBranding: Boolean,
    @Json(name = "premium_uses_last_48hrs") val premiumUsesLast48hrs: Number,
    @Json(name = "max_canvas_size") val maxCanvasSize: Number
)
