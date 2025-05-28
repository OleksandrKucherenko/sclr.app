package com.ab.sclr.ui.compose;

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.ab.sclr.R
import com.ab.sclr.data.overlays.Overlay
import com.ab.sclr.ui.canvas.CanvasViewModel
import me.saket.telephoto.zoomable.coil3.ZoomableAsyncImage
import me.saket.telephoto.zoomable.rememberZoomableImageState
import timber.log.Timber

@Composable
fun OverlaySelector(
    modifier: Modifier = Modifier,
    onItemClick: (Overlay) -> Unit,
    viewModel: CanvasViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        Timber.i("OverlaySelector: %s", state.overlays.size)
        state.overlays.forEach { category ->
            Timber.i("OverlaySelector: %s %s", category.title, category.items.size)
            category.items.forEach { overlay ->
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.0f)
                    ) {
                        val state = rememberZoomableImageState()

                        ZoomableAsyncImage(
                            state = state,
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(overlay.sourceUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = overlay.overlayName,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxSize()
                                .zIndex(0f),
                            onClick = { onItemClick(overlay) },
                        )

                        AnimatedVisibility(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                                .zIndex(1f),
                            visible = !state.isImageDisplayed
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.fillMaxSize().padding(32.dp),
                                strokeWidth = 4.dp,
                                color = MaterialTheme.colorScheme.secondary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            )
                        }
                    }
                }
            }
        }
    }
}
