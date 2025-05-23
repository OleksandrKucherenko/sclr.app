package com.ab.sclr.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ab.sclr.ui.UiDestinations
import com.ab.sclr.ui.theme.SclrcloneTheme

@Composable
fun SavedTabScreen(navController: NavController) {
    // Vertical Grid/List with created by user project (Skeleton)
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(8) {
            SkeletonItem(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable {
                        navController.navigate(UiDestinations.EDITOR) // Pass project ID
                    }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UiSavedTabScreenPreview() {
    val navController = rememberNavController()

    SclrcloneTheme {
        SavedTabScreen(navController = navController)
    }
}