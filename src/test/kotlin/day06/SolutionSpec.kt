package day06

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day06_test.txt"))).isEqualTo(11)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day06.txt"))).isEqualTo(6625)
        }
    }

    describe("Part B") {
        it("should resolve final scenario") {
            assertThat(partB(load("day06_test.txt"))).isEqualTo(6)
        }

        it("should resolve final scenario") {
            assertThat(partB(load("day06.txt"))).isEqualTo(3360)
        }
    }
})
