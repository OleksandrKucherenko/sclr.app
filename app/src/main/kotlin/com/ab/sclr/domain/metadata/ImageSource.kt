package com.ab.sclr.domain.metadata

import com.ab.sclr.domain.images.ImageSource

val ImageSource.category: String?
    get() = this.metadata[KnownMetadataKeys.CATEGORY.name]

val ImageSource.description: String?
    get() = this.metadata[KnownMetadataKeys.DESCRIPTION.name]

val ImageSource.author: String?
    get() = this.metadata[KnownMetadataKeys.AUTHOR.name]

val ImageSource.hashtags: String?
    get() = this.metadata[KnownMetadataKeys.HASHTAGS.name]