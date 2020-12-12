package day12

import kotlin.math.abs

private val inputPattern = "(\\w)(\\d+)".toRegex()

sealed class Direction
object N : Direction()
object S : Direction()
object W : Direction()
object E : Direction()
object L : Direction()
object R : Direction()
object F : Direction()

typealias Move = Pair<Direction, Int>

data class Position(val x: Int, val y: Int) {
    operator fun plus(position: Position) =
        Position(x + position.x, y + position.y)

    operator fun minus(position: Position) =
        Position(x - position.x, y - position.y)
}

class Ship {
    private var direction = 0
    private var position = Position(0, 0)

    fun go(moves: Collection<Move>) {
        moves.forEach { position = moveBy(it) }
    }

    fun position() = position.copy()

    private fun moveBy(move: Move): Position {
        return when {
            move.first == N -> position + Position(0, move.second)
            move.first == S -> position - Position(0, move.second)
            move.first == E -> position + Position(move.second, 0)
            move.first == W -> position - Position(move.second, 0)

            move.first == F -> when(direction) {
                0 ->  position + Position(move.second, 0)
                90 ->  position + Position(0, move.second)
                180 ->  position - Position(move.second, 0)
                270 ->  position - Position(0, move.second)
                -90 ->  position - Position(0, move.second)
                -180 ->  position - Position(move.second, 0)
                -270 ->  position + Position(0, move.second)
                else -> throw RuntimeException("Unknown direction $direction")
            }

            move.first == R -> {
                direction = (direction - move.second) % 360
                position
            }
            move.first == L -> {
                direction = (direction + move.second) % 360
                position
            }
            else -> throw RuntimeException("Unsupported move $move")
        }
    }
}

fun partA(lines: Collection<String>): Int {
    val moves = toMoves(lines)
    val map = Ship().also { it.go(moves) }
    return abs(map.position().x) + abs(map.position().y)
}

private fun toMoves(lines: Collection<String>) = lines
    .map { inputPattern.find(it)!!.destructured }
    .map { (direction, value) -> toMove(direction, value) }

private fun toMove(direction: String, value: String): Move = when (direction) {
    "N" -> N to value.toInt()
    "S" -> S to value.toInt()
    "W" -> W to value.toInt()
    "E" -> E to value.toInt()
    "L" -> L to value.toInt()
    "R" -> R to value.toInt()
    "F" -> F to value.toInt()
    else -> throw RuntimeException("Unsupported direction $direction")
}
