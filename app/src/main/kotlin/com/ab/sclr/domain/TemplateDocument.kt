package com.ab.sclr.domain

import com.squareup.moshi.JsonClass

enum class KnownMetadataKeys(name: String) {
    AUTHOR("author"),
    DESCRIPTION("description"),
    CATEGORY("category"),
    HASHTAGS("hashtags")
}

/**
 * Represents the overall template or canvas for a project.
 *
 * @property id Unique identifier for the template.
 * @property name User-defined name for the template.
 * @property slides List of slides contained within this template.
 * @property images List of all unique images used in this template.
 *                  This allows for referencing the same image across multiple layers/slides
 *                  without duplication of image data itself.
 * @property background Defines the background of the entire canvas.
 *                      Could be a color, gradient, or an image reference.
 * @property ratio The aspect ratio of the template (e.g., "16:9", "4:3", "1:1").
 * @property version Version of the document structure, for future migrations.
 * @property metadata Additional template-specific information (e.g., author, description, category, hashtag).
 */
@JsonClass(generateAdapter = true)
data class TemplateDocument(
    val id: String, // Or UUID
    val version: Int = 1,
    val name: String?,
    val slides: List<Slide> = listOf(),
    val images: List<ImageSource> = listOf(), // Centralized image definitions
    val background: Background = Background.empty(),
    val ratio: String = "1:1", // Consider an enum or a structured class for ratio
    val metadata: Map<String, String>? = mapOf()
)

// ref: https://kotlinlang.org/docs/extensions.html#extension-properties

val TemplateDocument.category: String?
    get() = this.metadata?.get(KnownMetadataKeys.CATEGORY.name)

val TemplateDocument.description: String?
    get() = this.metadata?.get(KnownMetadataKeys.DESCRIPTION.name)

val TemplateDocument.author: String?
    get() = this.metadata?.get(KnownMetadataKeys.AUTHOR.name)

val TemplateDocument.hashtags: String?
    get() = this.metadata?.get(KnownMetadataKeys.HASHTAGS.name)
