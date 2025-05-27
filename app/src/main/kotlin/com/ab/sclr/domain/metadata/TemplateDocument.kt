package com.ab.sclr.domain.metadata

import com.ab.sclr.domain.TemplateDocument

// ref: https://kotlinlang.org/docs/extensions.html#extension-properties

val TemplateDocument.category: String?
    get() = this.metadata[KnownMetadataKeys.CATEGORY.name]

val TemplateDocument.description: String?
    get() = this.metadata[KnownMetadataKeys.DESCRIPTION.name]

val TemplateDocument.author: String?
    get() = this.metadata[KnownMetadataKeys.AUTHOR.name]

val TemplateDocument.hashtags: String?
    get() = this.metadata[KnownMetadataKeys.HASHTAGS.name]