package day04

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day04_test.txt"))).isEqualTo(2)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day04.txt"))).isEqualTo(226)
        }
    }

    describe("Part B") {
        it("should resolve test scenario") {
            assertThat(partB(load("day04_test.txt"))).isEqualTo(2)
        }

        it("should resolve final scenario") {
            assertThat(partB(load("day04.txt"))).isEqualTo(160)
        }
    }
})
