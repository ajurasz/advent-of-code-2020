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
