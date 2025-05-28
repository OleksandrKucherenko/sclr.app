package com.ab.sclr.ui.canvas

import com.ab.sclr.data.overlays.OverlayCategory
import com.ab.sclr.domain.Slide
import com.ab.sclr.domain.TemplateDocument
import com.ab.sclr.domain.images.ImageSource
import com.ab.sclr.domain.layers.Layer
import java.util.UUID

data class CanvasState(
    val overlays: List<OverlayCategory> = emptyList(),

    val document: TemplateDocument = TemplateDocument(
        id = TemplateDocument.newId(),
        name = "New Project", slides = listOf(
            Slide(id = TemplateDocument.newId()),
            Slide(id = TemplateDocument.newId()),
            Slide(id = TemplateDocument.newId())
        )
    ),

    val selectedSlideId: String? = null,
    val selectedLayerId: String? = null,
    val selectedImageId: String? = null
)