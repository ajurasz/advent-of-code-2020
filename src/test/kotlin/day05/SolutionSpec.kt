package day05

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day05_test.txt"))).isEqualTo(820)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day05.txt"))).isEqualTo(896)
        }
    }

    describe("Part B") {
        it("should resolve final scenario") {
            assertThat(partB(load("day05.txt"))).isEqualTo(659)
        }
    }
})
