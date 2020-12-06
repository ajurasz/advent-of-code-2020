package day06

import chunked

fun partA(lines: Collection<String>) = lines.asSequence()
    .chunked { it.isBlank() }
    .map { it.joinToString(separator = "") }
    .map { it.toSet() }
    .map { it.count() }
    .sum()

fun partB(lines: Collection<String>) = lines.asSequence()
    .chunked { it.isBlank() }
    .map { it.joinToString(separator = "") to it.size }
    .map { (allAnswers, groupSize) ->
        return@map allAnswers.toList()
            .groupingBy { it }
            .eachCount()
            .filter { it.value == groupSize }
            .count()
    }
    .sum()
