package com.ab.sclr.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ab.sclr.domain.grid.KnownGrids

@Composable
fun GridSelector(
    modifier: Modifier,
    onItemClick: (KnownGrids) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4), // Or adaptive
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        KnownGrids.entries.forEach { knownGrid ->
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.0f)
                        .clickable { onItemClick( knownGrid ) }
                ) {
                    Column {
                        GridView(grid = knownGrid.grid, showGridId = false)
                    }
                }
            }
        }
    }
}