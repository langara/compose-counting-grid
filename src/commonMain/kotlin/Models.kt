import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

class CellModel {
    var content by mutableStateOf(0)
}

class RowModel(columnCount: Int) {
    val cells = Array(columnCount) { CellModel() }
}

class GridModel(private val rowCount: Int, private val columnCount: Int = rowCount) {
    val rows = Array(rowCount) { RowModel(columnCount) }

    private fun cell(rowIndex: Int, columnIndex: Int) = rows[rowIndex].cells[columnIndex]

    fun updateSingleCell(updateTopRowOnly: Boolean) {
        val target = cell(if (updateTopRowOnly) 0 else Random.nextInt(rowCount), Random.nextInt(columnCount))
        target.content = (target.content + 1).mod(10)
    }

    fun clear() {
        for (row in rows) {
            for (cell in row.cells) {
                cell.content = 0
            }
        }
    }

    override fun toString(): String = "${rowCount}x$columnCount (${rowCount * columnCount} cells)"
}

object Configuration {
    var pauseOnEachStep = mutableStateOf(false)
    var updateTopRowOnlyEnabled = mutableStateOf(false)
    var animationsEnabled = mutableStateOf(false)
    var topLevelRecompositionForced = mutableStateOf(false)
    var rowLevelRecompositionForced = mutableStateOf(false)
    var cellLevelRecompositionForced = mutableStateOf(false)
    var recomposeHighlightingEnabled = mutableStateOf(false)

    val elements = mapOf(
        "Pause on each step (100ms)" to pauseOnEachStep,
        "Update top row only" to updateTopRowOnlyEnabled,
        "Enable animations" to animationsEnabled,
        "Highlight recompositions" to recomposeHighlightingEnabled,
        "Force top-level recomposition" to topLevelRecompositionForced,
        "Force row-level recomposition" to rowLevelRecompositionForced,
        "Force cell-level recomposition" to cellLevelRecompositionForced
    )
}
