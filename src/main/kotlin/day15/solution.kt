package day15

fun partA(input: Collection<Int>, totalTurns: Int = 2020): Int {
    val spokenNumbers = input.toList().dropLast(1).mapIndexed { index, i -> i to index }.toMap(mutableMapOf())
    var lastSpoken = input.last()

    (spokenNumbers.count()..totalTurns - 2).forEach { idx ->
        if (spokenNumbers.containsKey(lastSpoken)) {
            val prevIdx = spokenNumbers.getValue(lastSpoken)
            spokenNumbers[lastSpoken] = idx
            lastSpoken = idx - prevIdx
        } else {
            spokenNumbers[lastSpoken] = idx
            lastSpoken = 0
        }
    }

    return lastSpoken
}
