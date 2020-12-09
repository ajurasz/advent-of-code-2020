fun <T> Sequence<T>.chunked(condition: (T) -> Boolean): Sequence<List<T>> = sequence {
    val buffer = mutableListOf<T>()
    for (element in this@chunked) {
        if (condition(element)) {
            yield(buffer)
            buffer.clear()
        } else {
            buffer += element
        }
    }
    if (buffer.isNotEmpty()) yield(buffer)
}

fun Sequence<Long>.untilSumEqualOrGreaterThan(value: Long): List<Long> {
    val accumulator = mutableListOf<Long>()
    for (element in this@untilSumEqualOrGreaterThan) {
        accumulator.add(element)
        if (accumulator.sum() >= value) {
            return accumulator
        }
    }
    return emptyList()
}
