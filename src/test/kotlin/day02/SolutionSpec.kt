package day02

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day02_test.txt"))).isEqualTo(2)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day02.txt"))).isEqualTo(467)
        }
    }

    describe("Part B") {
        it("should resolve test scenario") {
            assertThat(partB(load("day02_test.txt"))).isEqualTo(1)
        }

        it("should resolve final scenario") {
            assertThat(partB(load("day02.txt"))).isEqualTo(441)
        }
    }
})
