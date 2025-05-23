package com.ab.sclr.domain

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

@JsonClass(generateAdapter = true)
data class Row(
    val id: String,
    val size: Float,
    // If using WEIGHT, this is the weight. If FIXED, this is Dp/absolute.
    val sizeType: DimensionType = DimensionType.WEIGHT
)

@JsonClass(generateAdapter = true)
data class Column(
    val id: String,
    val size: Float,
    // If using WEIGHT, this is the weight. If FIXED, this is Dp/absolute.
    val sizeType: DimensionType = DimensionType.WEIGHT
)

enum class DimensionType {
    FIXED, // Absolute value (e.g., Dp)
    WEIGHT // Relative proportion of available space
}


/**
 * Represents a cell within a grid.
 * A cell is defined by its starting row/column and its span.
 *
 * @property id Unique identifier for the cell.
 * @property startRow Index of the starting row (0-based).
 * @property startColumn Index of the starting column (0-based).
 * @property rowSpan Number of rows this cell occupies (default is 1).
 * @property columnSpan Number of columns this cell occupies (default is 1).
 * @property contentLayerId Optional ID of an SclrLayer that is rendered *inside* this cell.
 *                          This allows for placing images, text, or even nested grids (though
 *                          the ADR disallowed nested grids for now, this provides a hook).
 *                          The contentLayer would have its position and size relative to this cell.
 * @property background Optional cell-specific background.
 * @property previewBackground Gray background color used for Grid layout preview.
 */
@JsonClass(generateAdapter = true)
data class Cell(
    val id: String, // Or UUID
    val startRow: Int,
    val startColumn: Int,
    val rowSpan: Int = 1,
    val columnSpan: Int = 1,
    val contentLayerId: String? = null, // ID of an SclrLayer placed *in* this cell
    // The actual SclrLayer would be in SclrSlide.layers list
    val background: Background? = null,
    val previewBackground: Background? = null
)
