package day09

import untilSumEqualOrGreaterThan

data class Encryption(val num: Long, val preamble: Set<Long>)
sealed class Range
object InvalidRange: Range()
data class ValidRange(val nums: List<Long>): Range() {
    fun weakness(): Long = nums.sorted().let { it.first() + it.last() }
}

fun partA(lines: Collection<Long>, preambleSize: Int = 25) = lines.windowed(preambleSize + 1).asSequence()
    .map { Encryption(it.last(), it.dropLast(1).toSet()) }
    .filter { isNotValid(it) }
    .first()
    .num

private fun isNotValid(encryption: Encryption) =
    encryption.preamble.any { encryption.preamble.contains(encryption.num - it) }.not()

fun partB(lines: Collection<Long>, sum: Long) = (0..lines.count()).asSequence()
    .map { lines.drop(it) }
    .map { toRanges(sum, it) }
    .filterIsInstance(ValidRange::class.java)
    .first()
    .weakness()

private fun toRanges(sum: Long, numbers: List<Long>): Range {
    val result = numbers.asSequence().untilSumEqualOrGreaterThan(sum)
    return when (result.sum()) {
        sum -> ValidRange(result)
        else -> InvalidRange
    }
}
