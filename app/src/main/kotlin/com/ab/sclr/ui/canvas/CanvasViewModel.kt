package com.ab.sclr.ui.canvas

import androidx.compose.ui.geometry.Rect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ab.sclr.data.overlays.OverlaysEndpoint
import com.ab.sclr.domain.Slide
import com.ab.sclr.domain.images.ImageSource
import com.ab.sclr.domain.layers.Layer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CanvasViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var api: OverlaysEndpoint

    private val _state = MutableStateFlow(CanvasState())
    val state: StateFlow<CanvasState> = _state

    /** Runtime data, allows to store object ID to bounds Rect mapping. */
    val bounds: MutableMap<String, Rect> = mutableMapOf()

    fun loadOverlays() {
        viewModelScope.launch {
            val downloaded = api.listOverlays()
            _state.update { it.copy(overlays = downloaded) }
        }
    }

    fun addSlide() {
        _state.update {
            it.copy(
                document = it.document.addSlide(
                    Slide(id = UUID.randomUUID().toString())
                )
            )
        }
    }

    fun removeLastSlide() {
        if (state.value.document.slides.isEmpty()) return

        // FIXME (olku): bad pattern for modifying the selected state, what if we delete
        //    slide that contained selected layer, grid, etc? it will make it complicated.

        _state.update {
            val last = it.document.slides.last()
            it.copy(
                document = it.document.removeSlide(last),
                selectedSlideId = if(last.id != it.selectedSlideId) it.selectedSlideId else null
            )
        }
    }

    fun selectSlide(slide: Slide) {
        _state.update { it.copy(selectedSlideId = slide.id) }
    }

    fun selectLayer(layer: Layer) {
        _state.update { it.copy(selectedLayerId = layer.id) }
    }

    fun selectImage(image: ImageSource) {
        _state.update { it.copy(selectedImageId = image.id) }

    }
}