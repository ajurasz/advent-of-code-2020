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

    fun distance() = abs(position.x) + abs(position.y)

    fun go(moves: Collection<Move>) {
        moves.forEach { position = moveBy(it) }
    }

    private fun moveBy(move: Move): Position {
        return when (move.first) {
            N -> position + Position(0, move.second)
            S -> position - Position(0, move.second)
            E -> position + Position(move.second, 0)
            W -> position - Position(move.second, 0)
            F -> when (direction) {
                0 -> position + Position(move.second, 0)
                90 -> position + Position(0, move.second)
                180 -> position - Position(move.second, 0)
                270 -> position - Position(0, move.second)
                else -> throw RuntimeException("Unknown direction $direction")
            }
            R -> {
                direction = ((direction - move.second) + 360) % 360
                position
            }
            L -> {
                direction = (direction + move.second) % 360
                position
            }
        }
    }
}

class Ship2 {
    private var position = Position(0, 0)
    private var waypoint = Position(10, 1)

    fun distance() = abs(position.x) + abs(position.y)

    fun go(moves: Collection<Move>) {
        moves.forEach(::moveBy)
    }

    private fun moveBy(move: Move) {
        when (move.first) {
            N -> waypoint += Position(0, move.second)
            S -> waypoint -= Position(0, move.second)
            E -> waypoint += Position(move.second, 0)
            W -> waypoint -= Position(move.second, 0)
            F -> position = (1..move.second).fold(position) { acc, _ -> acc + waypoint }
            R -> (0 until (move.second / 90)).forEach {
                waypoint = Position(waypoint.y, waypoint.x * -1)
            }
            L -> (0 until (move.second / 90)).forEach {
                waypoint = Position(waypoint.y * -1, waypoint.x)
            }
        }
    }
}

fun partA(lines: Collection<String>): Int {
    val moves = toMoves(lines)
    return Ship().let {
        it.go(moves)
        it.distance()
    }
}

fun partB(lines: Collection<String>): Int {
    val moves = toMoves(lines)
    return Ship2().let {
        it.go(moves)
        it.distance()
    }
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
