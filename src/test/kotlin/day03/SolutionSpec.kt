package day03

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day03_test.txt"))).isEqualTo(7)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day03.txt"))).isEqualTo(274)
        }
    }

    describe("Part B") {
        it("should resolve test scenario") {
            assertThat(partB(load("day03_test.txt"))).isEqualTo(336)
        }

        it("should resolve final scenario") {
            assertThat(partB(load("day03.txt"))).isEqualTo(6050183040)
        }
    }
})
