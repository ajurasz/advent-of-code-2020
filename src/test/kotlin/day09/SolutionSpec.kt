package day09

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day09_test.txt"), 5)).isEqualTo(127)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day09.txt"))).isEqualTo(530627549)
        }
    }

    describe("Part B") {
        it("should resolve test scenario") {
            assertThat(partB(load("day09_test.txt"), 127)).isEqualTo(62)
        }

        it("should resolve final scenario") {
            assertThat(partB(load("day09.txt"), 530627549)).isEqualTo(77730285)
        }
    }
})
