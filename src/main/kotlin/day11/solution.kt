package day11

sealed class Field(val char: Char) {
    override fun toString() = "$char"
}

object Flor : Field('.')
object Empty : Field('L')
object Occupied : Field('#')
typealias Grid = Array<Array<Field>>

class Layout(private val grid: Grid) {
    private val columns = grid[0].size
    private val rows = grid.size

    fun simulate(): Layout {
        return Layout(grid.mapIndexed { r, row ->
            row.mapIndexed { c, field ->
                if (grid[r][c] is Flor) field else decide(r, c, field)
            }.toTypedArray()
        }.toTypedArray())
    }

    private fun decide(row: Int, col: Int, field: Field): Field {
        val neighbors = countNeighbors(row, col)
        return when {
            field is Empty && neighbors == 0 -> Occupied
            field is Occupied && neighbors >= 4 -> Empty
            else -> field
        }
    }

    private fun countNeighbors(row: Int, col: Int): Int {
        val adjacent = arrayListOf(
            1 to -1, 1 to 0, 1 to 1,
            0 to -1, 0 to 1,
            -1 to -1, -1 to 0, -1 to 1
        )

        return adjacent
            .filter { (dr, dc) -> row + dr in 0 until rows && col + dc in 0 until columns }
            .map { (dr, dc) -> grid[row + dr][col + dc] }
            .filterIsInstance(Occupied::class.java)
            .count()
    }

    fun countOccupied(): Int = grid.flatMap { row -> row.map { it } }.count { it is Occupied }

    fun sameAs(layout: Layout) = this.grid.contentDeepEquals(layout.grid)

    fun print() {
        grid.forEach { row ->
            row.forEach { col -> print(col) }
            println()
        }
        println()
    }
}

fun partA(lines: List<String>): Int {
    var layout = initLayout(lines)
    while (true) {
        val next = layout.simulate()
        if (layout.sameAs(next)) break
        layout = next
    }
    return layout.countOccupied()
}

private fun initLayout(lines: List<String>) = Layout(
    lines.map { row ->
        row.map { field ->
            when (field) {
                'L' -> Empty
                '#' -> Occupied
                else -> Flor
            }
        }.toTypedArray()
    }.toTypedArray()
)
