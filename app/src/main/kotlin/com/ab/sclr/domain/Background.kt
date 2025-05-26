package com.ab.sclr.domain

import android.graphics.Color
import com.squareup.moshi.JsonClass

enum class BackgroundType {
    COLOR,
    // GRADIENT,
    // IMAGE
}

/**
 * Defines the background properties for a template or other elements.
 *
 * @property type Type of background (e.g., COLOR, GRADIENT, IMAGE).
 * @property colorValue Hex color string or similar representation if type is COLOR.
 * @property gradientValue Data for gradient if type is GRADIENT. (Define SclrGradient class)
 * @property imageRef Reference to an SclrImageSource id if type is IMAGE.
 */
@JsonClass(generateAdapter = true)
data class Background(
    val type: BackgroundType,
    val colorValue: Int? = null, // e.g., "#RRGGBBAA"
    val imageRef: String? = null // Reference to SclrImageSource.id

    // val gradientValue: SclrGradient? = null, // Define this if needed


) {
    companion object {
        fun empty(): Background = Background(
            type = BackgroundType.COLOR,
            colorValue = Color.argb(0, 255, 255, 255),
            imageRef = null,
        )
    }
}
