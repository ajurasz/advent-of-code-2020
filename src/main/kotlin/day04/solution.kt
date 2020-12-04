package day04

import chunked

fun partA(lines: Collection<String>) = lines.asSequence()
    .chunked { it.isBlank() }
    .map { it.joinToString(separator = " ") }
    .map { createPassport(it) }
    .filter { it is ValidPassport }
    .count()

fun partB(lines: Collection<String>) = lines.asSequence()
    .chunked { it.isBlank() }
    .map { it.joinToString(separator = " ") }
    .map { createPassport(it, true) }
    .filter { it is ValidPassport }
    .count()

val colorPattern = Regex("#[a-f0-9]{6}")

sealed class Passport
object InvalidPassport : Passport()
data class ValidPassport(
    private val byr: Int,
    private val iyr: Int,
    private val eyr: Int,
    private val hgt: String,
    private val hcl: String,
    private val ecl: String,
    private val pid: String,
    private val cid: Int?,
    private val detailedValidation: Boolean
) : Passport() {
    init {
        if (detailedValidation && !isValid()) throw RuntimeException("Invalid data")
    }

    private fun isValid(): Boolean {
        return byr in 1920..2002 &&
                iyr in 2010..2020 &&
                validateExpirationYear(eyr) &&
                validateHeight(hgt) &&
                validateHairColor(hcl) &&
                validateEyeColor(ecl) &&
                validatePid(pid)
    }

    private fun countDigits(str: String): Int = str.filter { Character.isDigit(it) }.count()

    private fun validateExpirationYear(eyr: Int) = countDigits(eyr.toString()) == 4 && eyr in 2020..2030

    private fun validateHeight(hgt: String): Boolean {
        val (num, unit) = hgt.partition { Character.isDigit(it) }
        return when (unit) {
            "cm" -> num.toInt() in 150..193
            else -> num.toInt() in 59..76
        }
    }

    private fun validateHairColor(hcl: String) = hcl.matches(colorPattern)

    private fun validateEyeColor(ecl: String) =
        arrayListOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl)

    private fun validatePid(pid: String) = countDigits(pid) == 9
}

fun createPassport(raw: String, detailedValidation: Boolean = false): Passport {
    val attributes = raw.split(" ").associate {
        val (key, value) = it.split(":")
        key to value
    }

    return try {
        ValidPassport(
            attributes.getValue("byr").toInt(),
            attributes.getValue("iyr").toInt(),
            attributes.getValue("eyr").toInt(),
            attributes.getValue("hgt"),
            attributes.getValue("hcl"),
            attributes.getValue("ecl"),
            attributes.getValue("pid"),
            attributes["cid"]?.toInt(),
            detailedValidation
        )
    } catch (e: Exception) {
        InvalidPassport
    }
}

