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

    fun simulate() =
        newLayout(4, ::closeNeighborsCounter)

    fun simulate2() =
        newLayout(5, ::farNeighborsCounter)

    fun countOccupied(): Int = grid.flatMap { row -> row.map { it } }.count { it is Occupied }

    fun sameAs(layout: Layout) = this.grid.contentDeepEquals(layout.grid)

    private fun newLayout(tolerance: Int, neighborsCounter: (Int, Int) -> Int) = Layout(grid.mapIndexed { r, row ->
        row.mapIndexed { c, field ->
            if (grid[r][c] is Flor) field else decide(tolerance, r, c, field, neighborsCounter)
        }.toTypedArray()
    }.toTypedArray())

    private fun decide(tolerance: Int, row: Int, col: Int, field: Field, neighborsCounter: (Int, Int) -> Int): Field {
        val neighbors = neighborsCounter(row, col)
        return when {
            field is Empty && neighbors == 0 -> Occupied
            field is Occupied && neighbors >= tolerance -> Empty
            else -> field
        }
    }

    private fun closeNeighborsCounter(row: Int, col: Int) =
        adjacent
            .filter { (dr, dc) -> row + dr in 0 until rows && col + dc in 0 until columns }
            .map { (dr, dc) -> grid[row + dr][col + dc] }
            .filterIsInstance(Occupied::class.java)
            .count()

    private fun farNeighborsCounter(row: Int, col: Int) =
        adjacent
            .filter { (dr, dc) -> row + dr in 0 until rows && col + dc in 0 until columns }
            .mapNotNull { (dr, dc) -> findFarNeighbors(row, col, dr, dc) }
            .filterIsInstance(Occupied::class.java)
            .count()

    private fun findFarNeighbors(row: Int, col: Int, dr: Int, dc: Int) =
        generateSequence(row + dr to col + dc) { (r, c) -> r + dr to c + dc }
            .takeWhile { (r, c) -> r in 0 until rows && c in 0 until columns  }
            .map { (r, c) -> grid[r][c] }
            .firstOrNull { it is Empty || it is Occupied }

    companion object {
        private val adjacent = arrayListOf(
            1 to -1, 1 to 0, 1 to 1,
            0 to -1, 0 to 1,
            -1 to -1, -1 to 0, -1 to 1
        )
    }
}

fun partA(lines: List<String>) =
    runSimulations(initLayout(lines)) { layout -> layout.simulate() }.countOccupied()

fun partB(lines: List<String>) =
    runSimulations(initLayout(lines)) { layout -> layout.simulate2() }.countOccupied()

private fun runSimulations(layout: Layout, fn: (Layout) -> Layout): Layout {
    var previous: Layout = layout
    while (true) {
        val next = fn(previous)
        if (previous.sameAs(next)) break
        previous = next
    }
    return previous
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
