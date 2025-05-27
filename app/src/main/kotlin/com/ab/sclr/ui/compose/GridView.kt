package com.ab.sclr.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutBaseScope
import androidx.constraintlayout.compose.Dimension
import com.ab.sclr.domain.grid.Grid
import com.ab.sclr.domain.grid.KnownGrids

object GrayColors {
    // Define a list of gray colors for cells
    val specter = listOf(
        Color(0xFFD6D6D6),
        Color(0xFFA9A9A9),
        Color(0xFFCCCCCC),
        Color(0xFFBDBDBD),
        Color(0xFFEEEEEE),
        Color(0xFFF5F5F5),
        Color(0xFF9E9E9E),
        Color(0xFFA0A0A0),
        Color(0xFF8D8D8D),
        Color(0xFFE0E0E0),
        Color(0xFFC0C0C0),
        Color(0xFFB0B0B0),
    )
}

/**
 * A Composable function that renders a Grid object using ConstraintLayout.
 *
 * @param grid The Grid object to render.
 * @param modifier Modifier to be applied to the ConstraintLayout.
 * @param showGridId Whether to display the cell IDs as text within each cell.
 */
@Composable
fun GridView(grid: Grid, modifier: Modifier = Modifier, showGridId: Boolean = false) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        // Create horizontal guidelines
        val hGuidelines = mutableListOf<ConstraintLayoutBaseScope.HorizontalAnchor>()
        var currentTopFraction = 0f

        hGuidelines.add(createGuidelineFromTop(currentTopFraction)) // Top edge guideline

        grid.rows.forEach { row ->
            currentTopFraction += row.size
            // Ensure fraction does not exceed 1.0f due to potential float inaccuracies
            hGuidelines.add(createGuidelineFromTop(currentTopFraction.coerceAtMost(1.0f)))
        }
        // If rows don't sum to 1.0f (which they should), ensure the last guideline is at the bottom.
        // This is usually handled if the sum of row.size is 1.0f.
        // If hGuidelines.last() isn't already at 1.0f, one could explicitly add createGuidelineFromTop(1f)
        // but the loop structure with coerceAtMost should be sufficient if data is correct.

        // Create vertical guidelines
        val vGuidelines = mutableListOf<ConstraintLayoutBaseScope.VerticalAnchor>()
        var currentStartFraction = 0f
        vGuidelines.add(createGuidelineFromStart(currentStartFraction)) // Start edge guideline

        grid.columns.forEach { column ->
            currentStartFraction += column.size
            // Ensure fraction does not exceed 1.0f
            vGuidelines.add(createGuidelineFromStart(currentStartFraction.coerceAtMost(1.0f)))
        }

        grid.cells.forEachIndexed { index, cellData ->
            // Create a unique reference for each cell to constrain it
            val cellRef = createRef()
            Box(
                modifier = Modifier
                    .constrainAs(cellRef) {
                        top.linkTo(hGuidelines[cellData.startRow])
                        bottom.linkTo(hGuidelines[cellData.startRow + cellData.rowSpan])
                        start.linkTo(vGuidelines[cellData.startColumn])
                        end.linkTo(vGuidelines[cellData.startColumn + cellData.columnSpan])
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .background(GrayColors.specter[index % GrayColors.specter.size])
                    .padding(1.dp) // Small padding to distinguish cell borders visually
            ) {
                if (showGridId) {
                    Text(
                        text = grid.id,
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Black.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun PreviewAllGrids() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4), // Or adaptive
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        KnownGrids.entries.forEach { knownGrid ->
            item {
                Card (modifier = Modifier.fillMaxWidth().aspectRatio(1.0f)) {
                    Column {
                        GridView(grid = knownGrid.grid, showGridId = false)
                    }
                }
            }
        }
    }
}
