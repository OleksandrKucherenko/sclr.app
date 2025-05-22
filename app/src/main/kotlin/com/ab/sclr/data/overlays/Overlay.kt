package com.ab.sclr.data.overlays

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class Overlay(
    val id: Long,
    @Json(name = "overlay_name") val overlayName: String,
    @Json(name = "created_at") val createdAt: Date,
    @Json(name = "category_id") val categoryId: Long,
    @Json(name = "source_url") val sourceUrl: String,
    @Json(name = "is_premium") val isPremium: Boolean,
    @Json(name = "includes_scrl_branding") val includesScrlBranding: Boolean,
    @Json(name = "premium_uses_last_48hrs") val premiumUsesLast48hrs: Long,
    @Json(name = "max_canvas_size") val maxCanvasSize: Long
)
