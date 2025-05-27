package com.ab.sclr.domain.layers

import com.ab.sclr.domain.grid.Grid
import com.ab.sclr.domain.images.ImageUsage
import com.ab.sclr.domain.primitives.SclrPoint
import com.ab.sclr.domain.primitives.SclrSize
import com.ab.sclr.domain.primitives.SclrText
import com.squareup.moshi.JsonClass

/**
 * Represents a layer on a slide. Each layer can hold different types of content.
 *
 * @property id Unique identifier for the layer.
 * @property type The type of content this layer holds (e.g., IMAGE, TEXT, GRID).
 * @property zLevel Order of stacking for this layer within the slide. Higher values are on top.
 * @property imageContent Details if the layer is an image layer.
 * @property textContent Details if the layer is a text layer.
 * @property gridContent Details if the layer is a grid container layer.
 * @property stickerContent Details if the layer is a sticker (special type of image).
 * @property frameContent Details if the layer is a frame (special type of image/overlay).
 * @property position The X, Y coordinates of the top-left corner of the layer on the slide.
 * @property size The width and height of the layer.
 * @property rotationDegrees Rotation of the layer in degrees.
 * @property opacity Opacity of the layer (0.0 to 1.0).
 * @property isVisible Whether the layer is currently visible.
 */
@JsonClass(generateAdapter = true)
data class Layer(
    val id: String, // Or UUID
    val type: LayerType,
    val zLevel: Int,
    val position: SclrPoint = SclrPoint(0f, 0f), // Relative to slide
    val size: SclrSize, // Intrinsic size of the layer content or frame
    val rotationDegrees: Float = 0f,
    val opacity: Float = 1.0f,
    val isVisible: Boolean = true,

    // Content specific properties: Only one should be non-null based on 'type'
    val imageContent: ImageUsage? = null,
    val textContent: SclrText? = null,
    val gridContent: Grid? = null

    // Sticker and Frame can be specialized SclrImageUsage or have their own data classes
    // if they have significantly different properties beyond what SclrImageUsage offers.
    // For now, let's assume they can be handled by SclrImageUsage with specific configurations.
)

