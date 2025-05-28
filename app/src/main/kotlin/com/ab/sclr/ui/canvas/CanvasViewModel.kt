package com.ab.sclr.ui.canvas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ab.sclr.data.overlays.OverlaysEndpoint
import com.ab.sclr.domain.Slide
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

        _state.update {
            it.copy(
                document = it.document.removeSlide(it.document.slides.last())
            )
        }
    }
}