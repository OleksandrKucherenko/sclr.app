package com.ab.sclr.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ab.sclr.ui.UiDestinations

@Composable
fun PopularProjects(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Or adaptive
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(10) { index ->
            TemplateExplorerItem(
                templateName = "Template ${index + 1}",
                onItemClick = {
                    // Navigate to Preview screen with template ID
                    navController.navigate("${UiDestinations.PREVIEW}/${index + 1}")
                },
                onUseTemplateClick = {
                    // Navigate to Editor with template ID
                    navController.navigate("${UiDestinations.EDITOR}?templateId=${index + 1}")
                }
            )
        }
    }
}