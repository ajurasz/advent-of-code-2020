package day08

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day08_test.txt"))).isEqualTo(5)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day08.txt"))).isEqualTo(2003)
        }
    }

    describe("Part B") {
        it("should resolve test scenario") {
            assertThat(partB(load("day08_test.txt"))).isEqualTo(8)
        }

        it("should resolve final scenario") {
            assertThat(partB(load("day08.txt"))).isEqualTo(1984)
        }
    }
})
