package com.ab.sclr.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ab.sclr.ui.theme.SclrcloneTheme
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(navController: NavController, templateId: String? = null) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(true)

    // templateId will be non-null if navigated from "Template Explorer" or "Saved"
    Box(
        modifier = Modifier
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

@Preview(showBackground = true)
@Composable
fun UiEditorScreenPreview() {
    val navController = rememberNavController()

    SclrcloneTheme {
        EditorScreen(navController = navController)
    }
}