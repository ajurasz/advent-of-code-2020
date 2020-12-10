package day10

fun partA(lines: List<Int>) = (listOf(0) + lines.sorted().let { it + listOf(it.last() + 3) })
    .windowed(4, partialWindows = true)
    .map { takeNearestAdapter(it.first(), it.drop(1)) }
    .groupingBy { it }.eachCount()
    .run { getValue(1) * getValue(3) }

private fun takeNearestAdapter(jolt: Int, adapters: List<Int>, maxDiff: Int = 3) =
    adapters.map { it - jolt }.firstOrNull { it <= maxDiff } ?: 0

// idea from this thread: https://www.reddit.com/r/adventofcode/comments/kabi91/2020_day_10_closedform_mathematical_solution/gfaq3zj/
fun partB(lines: List<Int>): Long {
    val adapters = lines.sorted().let { it + listOf(it.last() + 3) }
    val paths = mutableMapOf(0 to 1L)

    adapters.forEach { adapter ->
        paths[adapter] = sumOfThreePreviousAdapterPaths(adapter, paths)
    }

    return paths.getValue(adapters.last())
}

private fun sumOfThreePreviousAdapterPaths(adapter: Int, adapters: Map<Int, Long>) =
    (1..3).map { adapters.getOrDefault(adapter - it, 0) }.sum()
