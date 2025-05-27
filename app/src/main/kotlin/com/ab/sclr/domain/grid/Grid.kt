package com.ab.sclr.domain.grid

import com.ab.sclr.domain.primitives.SclrPadding
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Grid(
    val id: String, // Or UUID
    val rows: List<Row> = listOf(),
    val columns: List<Column> = listOf(),
    val cells: List<Cell> = listOf(), // Explicit cells are more flexible for spanning
    val rowSpacing: Float = 0f,
    val columnSpacing: Float = 0f,
    val padding: SclrPadding = SclrPadding()
) {
    fun addRow(row: Row): Grid {
        return this.copy(rows = rows + row)
    }

    fun removeRow(row: Row): Grid {
        return this.copy(rows = rows - row)
    }

    fun withRow(row: Row, apply: (Row) -> Row): Grid {
        return withRow(row.id, apply)
    }

    fun withRow(rowId: String, apply: (Row) -> Row): Grid {
        return this.copy(rows = rows.map {
            if (it.id == rowId) apply(it) else it
        })
    }

    fun addColumn(column: Column): Grid {
        return this.copy(columns = columns + column)
    }

    fun removeColumn(column: Column): Grid {
        return this.copy(columns = columns - column)
    }

    fun withColumn(column: Column, apply: (Column) -> Column): Grid {
        return withColumn(column.id, apply)
    }

    fun withColumn(columnId: String, apply: (Column) -> Column): Grid {
        return this.copy(columns = columns.map {
            if (it.id == columnId) apply(it) else it
        })
    }

    fun addCell(cell: Cell): Grid {
        return this.copy(cells = cells + cell)
    }

    fun removeCell(cell: Cell): Grid {
        return this.copy(cells = cells - cell)
    }

    fun withCell(cell: Cell, apply: (Cell) -> Cell): Grid {
        return withCell(cell.id, apply)
    }

    fun withCell(cellId: String, apply: (Cell) -> Cell): Grid {
        return this.copy(cells = cells.map {
            if (it.id == cellId) apply(it) else it
        })
    }
}


