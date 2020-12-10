package day10

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day10_test.txt"))).isEqualTo(220)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day10.txt"))).isEqualTo(2574)
        }
    }

    describe("Part B") {
        it("should resolve test scenario") {
            assertThat(partB(load("day10_test.txt"))).isEqualTo(19208)
        }

        it("should resolve final scenario") {
                assertThat(partB(load("day10.txt"))).isEqualTo(2644613988352)
        }
    }
})
