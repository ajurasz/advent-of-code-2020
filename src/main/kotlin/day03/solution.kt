package day03

sealed class Cell
object Empty : Cell()
object Tree : Cell()
object Outside : Cell()

data class Move(val x: Int, val y: Int)
data class Position(val x: Int, val y: Int) {
    fun move(move: Move) = Position(x + move.x, y + move.y)
}

class Map(private val cells: Array<Array<Cell>>) {
    private val rows = cells.size
    private val columns = cells[0].size

    fun isBottom(position: Position) = position.y >= rows

    fun cell(position: Position) = if (position.y <= rows) cells[position.y][calculateX(position.x)] else Outside

    private fun calculateX(x: Int) = x % columns
}

fun partA(lines: Collection<String>) = stepUntilBottomReached(buildMap(lines), Move(3, 1))
    .filter { it is Tree }
    .count()

fun partB(lines: Collection<String>) = arrayListOf(Move(1, 1), Move(3, 1), Move(5, 1), Move(7, 1), Move(1, 2))
    .map { move ->
        stepUntilBottomReached(buildMap(lines), move)
            .filter { it is Tree }
            .count()
            .toLong()
    }
    .reduce { acc, i -> acc * i }

fun buildMap(lines: Collection<String>) = Map(lines.map { line ->
    line.map { if (it == '#') Tree else Empty }.toTypedArray()
}.toTypedArray())

fun stepUntilBottomReached(map: Map, move: Move) = sequence {
    var position = Position(0, 0)
    while (!map.isBottom(position)) {
        yield(map.cell(position))
        position = position.move(move)
    }
}
