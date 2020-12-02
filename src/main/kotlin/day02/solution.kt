package day02

fun partA(lines: Collection<String>) = processPasswords(lines) { character, data ->
    OccurrencePasswordPolicy(character, Occurrence.from(data))
}

fun partB(lines: Collection<String>) = processPasswords(lines) { character, data ->
    PositionPasswordPolicy(character, Position.from(data))
}

private fun processPasswords(lines: Collection<String>, policyProvider: (Char, String) -> PasswordPolicy) = lines
    .map { it.split(" ") }
    .map { Pair(policyProvider(it[1].first(), it[0]), it[2]) }
    .filter { (policy, password) -> policy.isValid(password) }
    .count()

sealed class PasswordPolicy {
    abstract fun isValid(password: String): Boolean
}

class OccurrencePasswordPolicy(private val character: Char, private val occurrence: Occurrence) : PasswordPolicy() {
    override fun isValid(password: String) = occurrence.isBetween(password.filter { it == character }.count())
}

class Occurrence private constructor(private val min: Int, private val max: Int) {
    companion object {
        fun from(range: String) = range.split("-").map { it.toInt() }.let { Occurrence(it[0], it[1]) }
    }

    fun isBetween(count: Int) = count in min..max
}

class PositionPasswordPolicy(private val character: Char, private val position: Position) : PasswordPolicy() {
    override fun isValid(password: String) = isNotSameCharacterAtPosition(password) && atLeastOneCharacterMatchOnPosition(password)

    private fun isNotSameCharacterAtPosition(password: String) =
        !(password[position.first] == character && password[position.second] == character)

    private fun atLeastOneCharacterMatchOnPosition(password: String) =
        password[position.first] == character || password[position.second] == character
}

class Position private constructor(val first: Int, val second: Int) {
    companion object {
        fun from(range: String) = range.split("-").map { it.toInt() }.let { Position(it[0] - 1, it[1] - 1) }
    }
}
