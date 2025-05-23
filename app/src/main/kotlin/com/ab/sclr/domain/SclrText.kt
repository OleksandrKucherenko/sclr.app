package com.ab.sclr.domain

import com.squareup.moshi.JsonClass

/**
 * Represents text content on a layer.
 *
 * @property text The actual text string.
 * @property fontName Name or reference to the font.
 * @property fontSize Size of the font.
 * @property color Hex color string of the text.
 * @property alignment Text alignment (e.g., LEFT, CENTER, RIGHT).
 * @property effects List of text effects (e.g., shadow, outline). (Define SclrTextEffect class)
 */
@JsonClass(generateAdapter = true)
data class SclrText(
    val text: String,
    val fontName: String = "default",
    val fontSize: Float,
    val color: String, // e.g., "#RRGGBBAA"
    val alignment: TextAlignment = TextAlignment.LEFT,
    // val effects: List<SclrTextEffect>? = null // Define SclrTextEffect if needed
)


enum class TextAlignment {
    LEFT, CENTER, RIGHT, JUSTIFY
}
