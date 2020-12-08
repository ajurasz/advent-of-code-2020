package day08

val INSTRUCTION_PATTERN = """([\w+]{3}) ([+-]\d+)""".toRegex()

sealed class Instruction
data class Acc(val value: Int) : Instruction()
data class Jmp(val value: Int) : Instruction()
data class Nop(val value: Int) : Instruction()

sealed class Result(open val value: Int)
data class Success(override val value: Int) : Result(value)
data class Failure(override val value: Int) : Result(value)

typealias Code = ArrayList<Instruction>

class Bootloader {
    private var pointer = 0
    private var accumulator = 0
    private val registry = mutableSetOf<Int>()

    fun run(code: Code, force: Boolean = true): Result {
        while (registry.add(pointer)) {
            if (force && code.size == pointer) {
                return Success(accumulator)
            } else {
                runInstruction(code, pointer)
            }
        }
        return Failure(accumulator)
    }

    private fun runInstruction(code: Code, index: Int) {
        when (val instruction = code[index]) {
            is Acc -> {
                accumulator += instruction.value
                pointer += 1
            }
            is Jmp -> pointer += instruction.value
            is Nop -> pointer += 1
        }
    }
}

fun partA(lines: Collection<String>): Int {
    val code = Code(lines.map(::toInstruction))
    return Bootloader().run(code).value
}

fun partB(lines: Collection<String>): Int {
    val code = Code(lines.map(::toInstruction))
    (0..code.size).forEach { index ->
        when (val result = Bootloader().run(newCode(index, code))) {
            is Success -> return result.value
        }
    }
    throw RuntimeException("Failed to find solution")
}

private fun toInstruction(line: String): Instruction {
    val (symbol, value) = INSTRUCTION_PATTERN.find(line)!!.destructured
    return when (symbol) {
        "acc" -> Acc(value.toInt())
        "jmp" -> Jmp(value.toInt())
        else -> Nop(value.toInt())
    }
}

private fun newCode(index: Int, code: Code) = code.mapIndexed { idx, instruction ->
    if (index == idx) {
        replaceInstruction(instruction)
    } else {
        instruction
    }
} as Code

private fun replaceInstruction(instruction: Instruction) = when (instruction) {
    is Jmp -> Nop(instruction.value)
    is Nop -> Jmp(instruction.value)
    else -> instruction
}
