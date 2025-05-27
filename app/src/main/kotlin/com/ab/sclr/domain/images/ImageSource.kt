package com.ab.sclr.domain.images

import com.squareup.moshi.JsonClass


/**
 * Defines an image source, which can be local or remote.
 * This is the central definition of an image, referenced by SclrImageUsage.
 *
 * @property id Unique identifier for this image source.
 * @property originalUri URI to the original image (local file path, remote URL).
 * @property width Original width of the image in pixels.
 * @property height Original height of the image in pixels.
 * @property type Type of the image (e.g., JPEG, PNG, REMOTE_PLACEHOLDER).
 * @property metadata Additional image-specific info (e.g., EXIF data).
 * @property isCached Whether the image is cached locally in project folder or not.
 */
@JsonClass(generateAdapter = true)
data class ImageSource(
    val id: String, // Or UUID
    val originalUri: String, // URI to the original image
    val width: Int = 0,
    val height: Int = 0,

    val type: ImageSourceType = ImageSourceType.LOCAL,
    val isCached: Boolean = false,
    val metadata: Map<String, String> = mapOf()
)
