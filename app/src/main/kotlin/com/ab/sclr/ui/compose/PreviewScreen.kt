package com.ab.sclr.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ab.sclr.R
import com.ab.sclr.ui.theme.SclrcloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewScreen(navController: NavController, templateId: String? = "N/A") {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    stringResource(
                        R.string.title_preview_template_N,
                        templateId ?: "N/A"
                    )) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.alt_icon_back))
                    }
                }
            )
        },
        bottomBar = {
            PreviewControls(
                onPrev = { /* TODO: previous slide */ },
                onNext = { /* TODO: next slide */ },
                onPlay = { /* TODO: start animating slides */ },
                onSave = { /* TODO: save slide as image, or if slides are several save as movie. */ }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Simulate Instagram-like preview
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f) // 70% of height
                    .background(Color.DarkGray) // Placeholder for Telephoto ZoomableImage
                    .clickable { /* Could allow tap to show/hide controls */ },
                contentAlignment = Alignment.Center
            ) {
                Text("First Slide Preview (Zoomable Image)", color = Color.White)
            }
        }
    }
}

@Composable
fun PreviewControls(
    onPrev: () -> Unit,
    onNext: () -> Unit,
    onPlay: () -> Unit, // Add isPlaying state for toggle
    onSave: () -> Unit
) {
    var isPlaying by remember { mutableStateOf(false) }
    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPrev) {
                Icon(Icons.Filled.SkipPrevious, stringResource(R.string.alt_icon_previous))
            }
            IconButton(onClick = {
                isPlaying = !isPlaying
                onPlay()
            }) {
                Icon(
                    if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                    if (isPlaying) stringResource(R.string.alt_icon_pause) else stringResource(R.string.alt_icon_play)
                )
            }
            IconButton(onClick = onNext) {
                Icon(Icons.Filled.SkipNext, stringResource(R.string.alt_icon_next))
            }
            IconButton(onClick = onSave) {
                Icon(Icons.Filled.SaveAlt, stringResource(R.string.alt_icon_save_as))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UiPreviewScreenPreview() {
    val navController = rememberNavController()

    SclrcloneTheme {
        PreviewScreen(navController = navController)
    }
}