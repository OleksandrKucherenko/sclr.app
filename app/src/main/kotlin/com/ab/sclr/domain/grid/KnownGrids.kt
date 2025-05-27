package com.ab.sclr.domain.grid

/**
 * Enum defining various pre-defined grid layouts.
 * Our grid calculations are based on proportions (Float values summing to 1.0f for each dimension).
 * The naming convention follows: `${NumberOfRows}Rows[_${Proportions}]_${NumberOfColumns}Columns[_${Proportions}]_${Uniqueness}`.
 * Proportions are omitted if a single row/column occupies 100%.
 * Numbers for rows/columns are written out (e.g., One, Two).
 * Proportions are listed as numbers (e.g., _50_50, _30_70).
 */
enum class KnownGrids(val grid: Grid) {
    OneRow_TwoColumns_50_50(
        Grid(
            id = "1",
            rows = listOf(Row(id = "R1", size = 1.0f)),
            columns = listOf(
                Column(id = "C1", size = 0.5f),
                Column(id = "C2", size = 0.5f)
            ),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    TwoRows_50_50_OneColumn(
        Grid(
            id = "2",
            rows = listOf(
                Row(id = "R1", size = 0.5f),
                Row(id = "R2", size = 0.5f)
            ),
            columns = listOf(Column(id = "C1", size = 1.0f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 3: 2 rows 50/50, 2 columns 50/50, first row - one cell, second row - two cells 50/50
    TwoRows_50_50_TwoColumns_50_50_TopMerged( // Adjusted name for clarity vs. standard 2x2
        Grid(
            id = "3",
            rows = listOf(
                Row(id = "R1", size = 0.5f),
                Row(id = "R2", size = 0.5f)
            ),
            columns = listOf(
                Column(id = "C1", size = 0.5f),
                Column(id = "C2", size = 0.5f)
            ),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 2), // First row, one cell
                Cell(id = "Cell2", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1), // Second row, first cell
                Cell(id = "Cell3", startRow = 1, startColumn = 1, rowSpan = 1, columnSpan = 1)  // Second row, second cell (corrected from sample)
            )
        )
    ),
    // Grid 4: 2 rows, 2 columns, first column - two cells, second column - one cell
    TwoRows_50_50_TwoColumns_50_50_LeftStacked(
        Grid(
            id = "4",
            rows = listOf(Row(id = "R1", size = 0.5f), Row(id = "R2", size = 0.5f)),
            columns = listOf(Column(id = "C1", size = 0.5f), Column(id = "C2", size = 0.5f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1), // First col, first cell
                Cell(id = "Cell2", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1), // First col, second cell
                Cell(id = "Cell3", startRow = 0, startColumn = 1, rowSpan = 2, columnSpan = 1)  // Second col, one cell
            )
        )
    ),
    // Grid 5: 2 rows, 2 columns, first column - one cell, second column - two cells
    TwoRows_50_50_TwoColumns_50_50_RightStacked(
        Grid(
            id = "5",
            rows = listOf(Row(id = "R1", size = 0.5f), Row(id = "R2", size = 0.5f)),
            columns = listOf(Column(id = "C1", size = 0.5f), Column(id = "C2", size = 0.5f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 2, columnSpan = 1), // First col, one cell
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1), // Second col, first cell
                Cell(id = "Cell3", startRow = 1, startColumn = 1, rowSpan = 1, columnSpan = 1)  // Second col, second cell
            )
        )
    ),
    // Grid 6: 2 rows, 2 columns, first row - two cells, second row - one cell
    TwoRows_50_50_TwoColumns_50_50_BottomMerged(
        Grid(
            id = "6",
            rows = listOf(Row(id = "R1", size = 0.5f), Row(id = "R2", size = 0.5f)),
            columns = listOf(Column(id = "C1", size = 0.5f), Column(id = "C2", size = 0.5f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1), // First row, first cell
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1), // First row, second cell
                Cell(id = "Cell3", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 2)  // Second row, one cell
            )
        )
    ),
    // Grid 7: 3 columns (implies 1 row, 33/33/33 proportions)
    OneRow_ThreeColumns_33_33_34(
        Grid(
            id = "7",
            rows = listOf(Row(id = "R1", size = 1.0f)),
            columns = listOf(
                Column(id = "C1", size = 0.33f),
                Column(id = "C2", size = 0.33f),
                Column(id = "C3", size = 0.34f) // Adjusted for sum to 1.0
            ),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 0, startColumn = 2, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 8: 3 rows (implies 1 column, 33/33/33 proportions)
    ThreeRows_33_33_34_OneColumn(
        Grid(
            id = "8",
            rows = listOf(
                Row(id = "R1", size = 0.33f),
                Row(id = "R2", size = 0.33f),
                Row(id = "R3", size = 0.34f) // Adjusted for sum to 1.0
            ),
            columns = listOf(Column(id = "C1", size = 1.0f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 2, startColumn = 0, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 9: 2 columns, 2 rows, cells proportions: row 1 - 30/70, row 2 - 70/30
    // Interpreted as: Rows are 70/30, Columns are 30/70.
    TwoRows_70_30_TwoColumns_30_70(
        Grid(
            id = "9",
            rows = listOf(Row(id = "R1", size = 0.7f), Row(id = "R2", size = 0.3f)),
            columns = listOf(Column(id = "C1", size = 0.3f), Column(id = "C2", size = 0.7f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 1, startColumn = 1, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 10: 2 columns, 2 rows, cells proportions: column 1 - 30/70, column 2 - 70/30
    // Interpreted as: Rows are 30/70, Columns are 70/30.
    TwoRows_30_70_TwoColumns_70_30(
        Grid(
            id = "10",
            rows = listOf(Row(id = "R1", size = 0.3f), Row(id = "R2", size = 0.7f)),
            columns = listOf(Column(id = "C1", size = 0.7f), Column(id = "C2", size = 0.3f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 1, startColumn = 1, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 11: 2 columns, 2 rows, 2 cells in each row, 50/50 (Standard 2x2)
    TwoRows_50_50_TwoColumns_50_50_Standard(
        Grid(
            id = "11",
            rows = listOf(Row(id = "R1", size = 0.5f), Row(id = "R2", size = 0.5f)),
            columns = listOf(Column(id = "C1", size = 0.5f), Column(id = "C2", size = 0.5f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 1, startColumn = 1, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 12: 2 rows, 3 columns. first row - one cell, second row - three cells 33/33/33
    TwoRows_50_50_ThreeColumns_33_33_34_TopMerged(
        Grid(
            id = "12",
            rows = listOf(Row(id = "R1", size = 0.5f), Row(id = "R2", size = 0.5f)),
            columns = listOf(
                Column(id = "C1", size = 0.33f),
                Column(id = "C2", size = 0.33f),
                Column(id = "C3", size = 0.34f)
            ),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 3),
                Cell(id = "Cell2", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 1, startColumn = 1, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 1, startColumn = 2, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 13: 2 columns, 3 rows, first column - three cells 33/33/33, second column - one cell
    ThreeRows_33_33_34_TwoColumns_50_50_RightMerged(
        Grid(
            id = "13",
            rows = listOf(
                Row(id = "R1", size = 0.33f),
                Row(id = "R2", size = 0.33f),
                Row(id = "R3", size = 0.34f)
            ),
            columns = listOf(Column(id = "C1", size = 0.5f), Column(id = "C2", size = 0.5f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 2, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 0, startColumn = 1, rowSpan = 3, columnSpan = 1)
            )
        )
    ),
    // Grid 14: 2 columns, 3 rows, first column - one cell, second column - three cells 33/33/33
    ThreeRows_33_33_34_TwoColumns_50_50_LeftMerged(
        Grid(
            id = "14",
            rows = listOf(
                Row(id = "R1", size = 0.33f),
                Row(id = "R2", size = 0.33f),
                Row(id = "R3", size = 0.34f)
            ),
            columns = listOf(Column(id = "C1", size = 0.5f), Column(id = "C2", size = 0.5f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 3, columnSpan = 1),
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 1, startColumn = 1, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 2, startColumn = 1, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 15: 2 rows, 3 columns, first row - three cells 33/33/33, second row - one cell
    TwoRows_50_50_ThreeColumns_33_33_34_BottomMerged(
        Grid(
            id = "15",
            rows = listOf(Row(id = "R1", size = 0.5f), Row(id = "R2", size = 0.5f)),
            columns = listOf(
                Column(id = "C1", size = 0.33f),
                Column(id = "C2", size = 0.33f),
                Column(id = "C3", size = 0.34f)
            ),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 0, startColumn = 2, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 3)
            )
        )
    ),
    // Grid 16: 4 columns, 1 row
    OneRow_FourColumns_25_25_25_25(
        Grid(
            id = "16",
            rows = listOf(Row(id = "R1", size = 1.0f)),
            columns = listOf(
                Column(id = "C1", size = 0.25f), Column(id = "C2", size = 0.25f),
                Column(id = "C3", size = 0.25f), Column(id = "C4", size = 0.25f)
            ),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 0, startColumn = 2, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 0, startColumn = 3, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 17: 4 rows, 1 column
    FourRows_25_25_25_25_OneColumn(
        Grid(
            id = "17",
            rows = listOf(
                Row(id = "R1", size = 0.25f), Row(id = "R2", size = 0.25f),
                Row(id = "R3", size = 0.25f), Row(id = "R4", size = 0.25f)
            ),
            columns = listOf(Column(id = "C1", size = 1.0f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 2, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 3, startColumn = 0, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 18: 5 cells, 2 rows, 3 columns. 2 cells in first row 50/50, 3 cells in second row 33/33/33
    // "50/50" for 2 cells over 3 columns is approximated by 1-span and 2-span cells.
    TwoRows_50_50_ThreeColumns_33_33_34_CustomLayout18(
        Grid(
            id = "18",
            rows = listOf(Row(id = "R1", size = 0.5f), Row(id = "R2", size = 0.5f)),
            columns = listOf(
                Column(id = "C1", size = 0.33f),
                Column(id = "C2", size = 0.33f),
                Column(id = "C3", size = 0.34f)
            ),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1), // First row, cell 1 (approx. 33%)
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 2), // First row, cell 2 (approx. 66%)
                Cell(id = "Cell3", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1), // Second row, cell 1
                Cell(id = "Cell4", startRow = 1, startColumn = 1, rowSpan = 1, columnSpan = 1), // Second row, cell 2
                Cell(id = "Cell5", startRow = 1, startColumn = 2, rowSpan = 1, columnSpan = 1)  // Second row, cell 3
            )
        )
    ),
    // Grid 19: 5 columns
    OneRow_FiveColumns_20_20_20_20_20(
        Grid(
            id = "19",
            rows = listOf(Row(id = "R1", size = 1.0f)),
            columns = listOf(
                Column(id = "C1", size = 0.2f), Column(id = "C2", size = 0.2f),
                Column(id = "C3", size = 0.2f), Column(id = "C4", size = 0.2f),
                Column(id = "C5", size = 0.2f)
            ),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 0, startColumn = 1, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 0, startColumn = 2, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 0, startColumn = 3, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell5", startRow = 0, startColumn = 4, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 20: 5 rows
    FiveRows_20_20_20_20_20_OneColumn(
        Grid(
            id = "20",
            rows = listOf(
                Row(id = "R1", size = 0.2f), Row(id = "R2", size = 0.2f),
                Row(id = "R3", size = 0.2f), Row(id = "R4", size = 0.2f),
                Row(id = "R5", size = 0.2f)
            ),
            columns = listOf(Column(id = "C1", size = 1.0f)),
            cells = listOf(
                Cell(id = "Cell1", startRow = 0, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell2", startRow = 1, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell3", startRow = 2, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell4", startRow = 3, startColumn = 0, rowSpan = 1, columnSpan = 1),
                Cell(id = "Cell5", startRow = 4, startColumn = 0, rowSpan = 1, columnSpan = 1)
            )
        )
    ),
    // Grid 21: 3x3 Rows x Columns
    ThreeRows_33_33_34_ThreeColumns_33_33_34(
        Grid(
            id = "21",
            rows = listOf(Row(id = "R1", size = 0.33f), Row(id = "R2", size = 0.33f), Row(id = "R3", size = 0.34f)),
            columns = listOf(Column(id = "C1", size = 0.33f), Column(id = "C2", size = 0.33f), Column(id = "C3", size = 0.34f)),
            cells = (0..2).flatMap { r ->
                (0..2).map { c ->
                    Cell(id = "Cell${r * 3 + c + 1}", startRow = r, startColumn = c, rowSpan = 1, columnSpan = 1)
                }
            }
        )
    ),
    // Grid 22: 2x4 Rows x Columns
    TwoRows_50_50_FourColumns_25_25_25_25(
        Grid(
            id = "22",
            rows = listOf(Row(id = "R1", size = 0.5f), Row(id = "R2", size = 0.5f)),
            columns = listOf(
                Column(id = "C1", size = 0.25f), Column(id = "C2", size = 0.25f),
                Column(id = "C3", size = 0.25f), Column(id = "C4", size = 0.25f)
            ),
            cells = (0..1).flatMap { r ->
                (0..3).map { c ->
                    Cell(id = "Cell${r * 4 + c + 1}", startRow = r, startColumn = c, rowSpan = 1, columnSpan = 1)
                }
            }
        )
    ),
    // Grid 23: 2x3 Rows x Columns
    TwoRows_50_50_ThreeColumns_33_33_34(
        Grid(
            id = "23",
            rows = listOf(Row(id = "R1", size = 0.5f), Row(id = "R2", size = 0.5f)),
            columns = listOf(
                Column(id = "C1", size = 0.33f),
                Column(id = "C2", size = 0.33f),
                Column(id = "C3", size = 0.34f)
            ),
            cells = (0..1).flatMap { r ->
                (0..2).map { c ->
                    Cell(id = "Cell${r * 3 + c + 1}", startRow = r, startColumn = c, rowSpan = 1, columnSpan = 1)
                }
            }
        )
    ),
    // Grid 24: 2x4 Rows x Columns (Interpreted as 4Rx2C to be unique from Grid 22)
    FourRows_25_25_25_25_TwoColumns_50_50(
        Grid(
            id = "24",
            rows = listOf(
                Row(id = "R1", size = 0.25f), Row(id = "R2", size = 0.25f),
                Row(id = "R3", size = 0.25f), Row(id = "R4", size = 0.25f)
            ),
            columns = listOf(Column(id = "C1", size = 0.5f), Column(id = "C2", size = 0.5f)),
            cells = (0..3).flatMap { r ->
                (0..1).map { c ->
                    Cell(id = "Cell${r * 2 + c + 1}", startRow = r, startColumn = c, rowSpan = 1, columnSpan = 1)
                }
            }
        )
    ),
    // Grid 25: 3x3 rows x Columns (Interpreted as 3Rx2C to be unique from Grid 21)
    ThreeRows_33_33_34_TwoColumns_50_50(
        Grid(
            id = "25",
            rows = listOf(
                Row(id = "R1", size = 0.33f),
                Row(id = "R2", size = 0.33f),
                Row(id = "R3", size = 0.34f)
            ),
            columns = listOf(Column(id = "C1", size = 0.5f), Column(id = "C2", size = 0.5f)),
            cells = (0..2).flatMap { r ->
                (0..1).map { c ->
                    Cell(id = "Cell${r * 2 + c + 1}", startRow = r, startColumn = c, rowSpan = 1, columnSpan = 1)
                }
            }
        )
    ),
    // Grid 26: 4x4 rows x Columns
    FourRows_25_25_25_25_FourColumns_25_25_25_25(
        Grid(
            id = "26",
            rows = listOf(
                Row(id = "R1", size = 0.25f), Row(id = "R2", size = 0.25f),
                Row(id = "R3", size = 0.25f), Row(id = "R4", size = 0.25f)
            ),
            columns = listOf(
                Column(id = "C1", size = 0.25f), Column(id = "C2", size = 0.25f),
                Column(id = "C3", size = 0.25f), Column(id = "C4", size = 0.25f)
            ),
            cells = (0..3).flatMap { r ->
                (0..3).map { c ->
                    Cell(id = "Cell${r * 4 + c + 1}", startRow = r, startColumn = c, rowSpan = 1, columnSpan = 1)
                }
            }
        )
    );

    // Companion object or helper functions could be added here if needed
}