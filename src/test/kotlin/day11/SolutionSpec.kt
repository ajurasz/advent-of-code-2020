package day11

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day11_test.txt"))).isEqualTo(37)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day11.txt"))).isEqualTo(2316)
        }
    }

    describe("Part B") {
        it("should resolve test scenario") {
            assertThat(partB(load("day11_test.txt"))).isEqualTo(26)
        }

        it("should resolve final scenario") {
                assertThat(partB(load("day11.txt"))).isEqualTo(2128)
        }
    }
})
