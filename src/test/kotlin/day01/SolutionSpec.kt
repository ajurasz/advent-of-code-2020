package day01

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day01_test.txt"))).isEqualTo(514579)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day01.txt"))).isEqualTo(658899)
        }
    }

    describe("Part B") {
        it("should resolve test scenario") {
            assertThat(partB(load("day01_test.txt"))).isEqualTo(241861950)
        }

        it("should resolve final scenario") {
            assertThat(partB(load("day01.txt"))).isEqualTo(155806250)
        }
    }
})