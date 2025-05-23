package com.ab.sclr.domain

import com.squareup.moshi.JsonClass

/**
 * Represents an instance of an image being used on a layer.
 * This references a centrally defined SclrImageSource.
 *
 * @property imageRef Reference to the SclrImageSource.id.
 * @property anchorPosition The X, Y anchor point for transformations (e.g., center of the image).
 *                          Expressed as a ratio of the image's own dimensions (0.0 to 1.0).
 * @property crop Defines the cropping rectangle for the image. Null if no crop.
 *                Coordinates are relative to the original image dimensions.
 * @property transformationMatrix Advanced transformations (scale, rotate, skew, translate).
 *                                  If not provided, simple scale, rotate, shift from layer
 *                                  or image properties can be used.
 * @property maskPath Optional path defining a mask for the image. (Define SclrPath class)
 * @property filters List of filters applied to this image instance. (Define SclrFilter class)
 */
@JsonClass(generateAdapter = true)
data class ImageUsage(
    val imageRef: String, // References SclrImageSource.id
    val anchorPosition: SclrPoint = SclrPoint(0.5f, 0.5f), // Default to center
    val crop: SclrRect? = null, // Crop rectangle relative to original image
    // val transformationMatrix: SclrMatrix? = null, // Define SclrMatrix if needed for complex transforms
    // val maskPath: SclrPath? = null,              // Define SclrPath for masking
    // val filters: List<SclrFilter>? = null      // Define SclrFilter for image effects
)