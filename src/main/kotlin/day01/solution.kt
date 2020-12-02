package day01

fun partA(numbers: Collection<Int>): Int {
    for (num in numbers) {
        val missing = 2020 - num
        if (numbers.contains(missing)) return num * missing
    }
    throw RuntimeException("Number not found")
}

fun partB(numbers: Collection<Int>): Int {
    val missing: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()
    for ((i, a) in numbers.withIndex()) {
        for ((j, b) in numbers.withIndex()) {
            if (i != j)
                missing[(2020 - a - b)] = Pair(a, b)
        }
    }
    for (num in numbers) {
        if (missing.containsKey(num)) return missing.getValue(num).let { num * it.first * it.second }
    }

    throw RuntimeException("Number not found")
}
