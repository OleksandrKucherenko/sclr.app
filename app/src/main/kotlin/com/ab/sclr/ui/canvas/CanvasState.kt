package com.ab.sclr.ui.canvas

import com.ab.sclr.data.overlays.OverlayCategory
import com.ab.sclr.domain.Slide
import com.ab.sclr.domain.TemplateDocument
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
    )
)