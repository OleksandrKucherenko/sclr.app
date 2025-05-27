package com.ab.sclr.domain.grid

import com.ab.sclr.domain.primitives.SclrPadding
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Grid(
    val id: String, // Or UUID
    val rows: List<Row>,
    val columns: List<Column>,
    val cells: List<Cell>, // Explicit cells are more flexible for spanning
    val rowSpacing: Float = 0f,
    val columnSpacing: Float = 0f,
    val padding: SclrPadding = SclrPadding()
)


