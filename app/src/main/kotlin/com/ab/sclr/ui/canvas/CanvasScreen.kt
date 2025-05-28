package com.ab.sclr.ui.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.ab.sclr.domain.Slide
import com.ab.sclr.domain.TemplateDocument
import timber.log.Timber

/** Defaults for rendering. */
object D {
    const val RATION_PORTRAIT = 9f / 16f
    const val SLIDE_WIDTH = 200
    const val SLIDE_HEIGHT = SLIDE_WIDTH * (1 / RATION_PORTRAIT)
    const val OVERLAP = SLIDE_WIDTH / 2
    const val TOP = 40
    const val MIN_ZOOM = 0.5f
    const val MAX_ZOOM = 3f
}

@Composable
fun CanvasScreen(
    viewModel: CanvasViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var offset by remember { mutableStateOf(Offset(-10.0f, -10.0f)) }
    val zoom = remember { mutableStateOf(1.0f) }
    var viewportSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clipToBounds()
            .onSizeChanged { viewportSize = it.toSize() }
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, scale, rotate ->
                    Timber.i("Centroid: $centroid, Pan: $pan, Scale: $scale, Rotate: $rotate")

                    // reserve space at list for one "empty slide" or display normal slides
                    val minSpace = if (state.document.slides.isEmpty()) 1 else state.document.slides.size

                    val oldScale = zoom.value
                    val newScale =
                        (zoom.value * scale).coerceIn(D.MIN_ZOOM, D.MAX_ZOOM)
                    val nextOffset =
                        (offset + centroid / oldScale) - (centroid / newScale + pan / oldScale)

                    // limits should be in Pixels
                    val minX = -(D.OVERLAP * newScale).dp.toPx()
                    val maxX = ((D.SLIDE_WIDTH * minSpace - D.OVERLAP) * newScale).dp.toPx()
                    val minY = -(D.OVERLAP * newScale).dp.toPx()
                    val maxY = ((D.SLIDE_HEIGHT - D.OVERLAP) * newScale).dp.toPx()

                    offset = Offset(
                        nextOffset.x.coerceIn(minX, maxX),
                        nextOffset.y.coerceIn(minY, maxY)
                    )
                    zoom.value = newScale

                    Timber.i("axis-X min: $minX, max: $maxX - $offset");
                    Timber.i("axis-Y min: $minY, max: $maxY - $offset");
                }
            }
    ) {
        // reserve space at list for one "empty slide" or display normal slides
        val minSpace = if (state.document.slides.isEmpty()) 1 else state.document.slides.size

        // WARNING: keep nested Boxes. Parent Box used for capturing pointer input, nested for
        // drawing Slides, Layouts, Images and etc.
        Box(
            modifier = Modifier
                .graphicsLayer(
                    scaleX = zoom.value,
                    scaleY = zoom.value,
                    translationX = -(offset.x + 0f),
                    translationY = -(offset.y + 0f),
                )
                .width((D.OVERLAP * 2 + minSpace * D.SLIDE_WIDTH).dp)
        ) {
            if (state.document.slides.isEmpty()) {
                EmptySlide()
            } else {
                // all elements should use absolute position inside this box
                // size and position are absolute!
                state.document.slides.forEachIndexed { index, slide ->
                    SlideItem(document = state.document, slide = slide, offsetIndex = index)
                }
            }
        }
    }
}

@Composable
fun SlideItem(
    document: TemplateDocument,
    slide: Slide,
    modifier: Modifier = Modifier,
    offsetIndex: Int
) {
    // TODO: Determine aspect ratio from slide.ratio or template.ratio
    val aspectRatio = when (document.ratio) {

        // Assuming TemplateDocument.Ratio will have defined values
        // For now, using a placeholder or a default
        else -> D.RATION_PORTRAIT
    }

    var viewportSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier
            .onSizeChanged { viewportSize = it.toSize() }
            .width(D.SLIDE_WIDTH.dp)
            .offset((offsetIndex * D.SLIDE_WIDTH).dp, D.TOP.dp)
            .aspectRatio(aspectRatio)
            .background(Color.White)
            .border(1.dp, Color.Gray.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Slide ID: ${slide.id.substring(0, 4)}\nLayers: ${slide.layers.size}",
            fontSize = 12.sp
        )
    }
}

