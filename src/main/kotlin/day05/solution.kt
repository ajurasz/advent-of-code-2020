package day05

fun partA(lines: Collection<String>) = lines
    .map { it.take(7) to it.takeLast(3) }
    .map { find(it.first, 0..127) * 8 + find(it.second, 0..7) }
    .maxOrNull()

fun partB(lines: Collection<String>) = lines
    .map { it.take(7) to it.takeLast(3) }
    .map { find(it.first, 0..127) * 8 + find(it.second, 0..7) }
    .sorted()
    .zip((53..896).toList())
    .map { it to (it.first - it.second) }
    .first { it.second > 0 }.first.second

tailrec fun find(code: String, rows: IntRange): Int {
    val center = rows.count()/2
    return when(code.take(1)) {
        "F", "L" -> find(code.drop(1), rows.first until (rows.first+center))
        "B", "R" -> find(code.drop(1), (rows.first+center)..rows.last)
        else -> rows.first
    }
}
