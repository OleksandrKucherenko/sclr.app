package com.ab.sclr.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ab.sclr.ui.theme.SclrcloneTheme

@Composable
fun EditorScreen(navController: NavController, templateId: String? = null) {
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
            Button(onClick = { /* TODO: Implement Grids Logic */ }) {
                Text("Select Grids")
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