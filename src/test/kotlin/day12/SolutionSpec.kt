package day12

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import load

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(load("day12_test.txt"))).isEqualTo(25)
        }

        it("should resolve final scenario") {
            assertThat(partA(load("day12.txt"))).isEqualTo(319)
        }
    }
})
