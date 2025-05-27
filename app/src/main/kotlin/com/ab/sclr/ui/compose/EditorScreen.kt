package com.ab.sclr.ui.compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.CropLandscape
import androidx.compose.material.icons.filled.CropOriginal
import androidx.compose.material.icons.filled.CropPortrait
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.FitScreen
import androidx.compose.material.icons.filled.FormatColorReset
import androidx.compose.material.icons.filled.Gradient
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Splitscreen
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
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
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(navController: NavController, templateId: String? = null) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(true)

    val todo = {
        Toast.makeText(navController.context, "Not Implemented Yet!", Toast.LENGTH_SHORT).show()
    }

    val showGrid = { showBottomSheet = true }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(
                            R.string.title_edit_project_N,
                            templateId ?: "N/A"
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(
                                R.string.alt_icon_back
                            )
                        )
                    }
                }
            )
        },
        bottomBar = {
            EditorControls(handlers = object : ControlsHandler {
                override fun onSlideAddClick() = todo()
                override fun onSlideRemoveClick() = todo()
                override fun onSelectGridClick()  = showGrid()
                override fun onSelectOverlayClick() = todo()
                override fun onPictureSelectClick() = todo()
                override fun onNoOp() = todo()
            })
        }
    ) { paddingValues ->
        // templateId will be non-null if navigated from "Template Explorer" or "Saved"
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.LightGray), // Placeholder
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Editor Screen",
                    style = MaterialTheme.typography.headlineMedium
                )
                if (templateId != null) {
                    Text("Editing template: $templateId")
                } else {
                    Text("Editing a blank project")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* TODO: Implement Image Selector Logic */ }) {
                    Text("Select Image (Camera/Photos/Remote)")
                }
                Button(onClick = { showBottomSheet = true }) {
                    Text("Select Grids")
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            GridSelector(
                modifier = Modifier.height(350.dp),
                onItemClick = {
                    // TODO (olku): add Layer with Selected grid
                    Timber.i("Selected grid: %s", it.grid)

                    showBottomSheet = false
                })
        }
    }
}


@Composable
fun EditorControls(handlers: ControlsHandler) {
    // can be displayed only one set of controls at a time
    var isBackground by remember { mutableStateOf(false) }
    var isLayers by remember { mutableStateOf(false) }
    var isSlides by remember { mutableStateOf(false) }
    var isRatio by remember { mutableStateOf(false) }

    val none: () -> Unit =
        { isBackground = false; isLayers = false; isSlides = false; isRatio = false }
    val background: () -> Unit = { none(); isBackground = true }
    val layers: () -> Unit = { none(); isLayers = true }
    val slides: () -> Unit = { none(); isSlides = true }
    val ratio: () -> Unit = { none(); isRatio = true }

    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isBackground) {
                IconButton(onClick = { none(); handlers.onNoOp() }) {
                    Icon(Icons.Filled.Colorize, "Solid")
                }
                IconButton(onClick = { none(); handlers.onNoOp() }) {
                    Icon(Icons.Filled.Gradient, "Gradient")
                }
                IconButton(onClick = { none(); handlers.onNoOp() }) {
                    Icon(Icons.Filled.FormatColorReset, "Reset")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { none() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                }
            } else if (isLayers) {
                IconButton(onClick = { none(); handlers.onPictureSelectClick() }) {
                    Icon(Icons.Filled.Image, "Image")
                }
                IconButton(onClick = { none(); handlers.onSelectOverlayClick() }) {
                    Icon(Icons.Filled.Stars, "Overlay")
                }
                IconButton(onClick = { none(); handlers.onNoOp() }) {
                    Icon(Icons.Filled.TextFields, "Text")
                }
                IconButton(onClick = { none(); handlers.onSelectGridClick() }) {
                    Icon(Icons.Filled.GridOn, "Grid")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { none() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                }
            } else if (isSlides) {
                IconButton(onClick = { handlers.onSlideAddClick() }) {
                    Icon(Icons.Filled.Remove, "Remove")
                }
                IconButton(onClick = { handlers.onSlideRemoveClick() }) {
                    Icon(Icons.Filled.Add, "Add")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { none() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                }
            } else if (isRatio) {
                TextButton(onClick = { none(); handlers.onNoOp() }) {
                    Icon(Icons.Filled.CropLandscape, "Landscape")
                    Text("16:9")
                }
                TextButton(onClick = { none(); handlers.onNoOp() }) {
                    Icon(Icons.Filled.CropSquare, "Square")
                    Text("1:1")
                }
                TextButton(onClick = { none(); handlers.onNoOp() }) {
                    Icon(Icons.Filled.CropPortrait, "Portrait")
                    Text("4:5")
                }
                TextButton(onClick = { none(); handlers.onNoOp() }) {
                    Icon(Icons.Filled.CropOriginal, "Stories")
                    Text("9:16")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { none() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                }
            } else {
                TextButton(onClick = { background() }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Palette, "Background")
                        Text("Colors", style = MaterialTheme.typography.labelSmall)
                    }
                }
                TextButton(onClick = { layers() }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Add, "Layers")
                        Text("Layers", style = MaterialTheme.typography.labelSmall)
                    }
                }
                TextButton(onClick = { ratio() }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.FitScreen, "Ratio")
                        Text("Ratio", style = MaterialTheme.typography.labelSmall)
                    }
                }
                TextButton(onClick = { slides() }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Splitscreen, "Slides")
                        Text("Slides", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UiEditorScreenPreview() {
    val navController = rememberNavController()

    SclrcloneTheme {
        EditorScreen(navController = navController)
    }
}