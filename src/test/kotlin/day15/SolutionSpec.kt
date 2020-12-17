package day15

import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class SolutionSpec : Spek({
    describe("Part A") {
        it("should resolve test scenario") {
            assertThat(partA(arrayListOf(0, 3, 6))).isEqualTo(436)
        }

        it("should resolve final scenario") {
            assertThat(partA(arrayListOf(1,0,18,10,19,6))).isEqualTo(441)
        }
    }

    describe("Part B") {
        it("should resolve test scenario") {
            assertThat(partA(arrayListOf(0, 3, 6), 30000000)).isEqualTo(175594)
        }

        it("should resolve final scenario") {
            assertThat(partA(arrayListOf(1,0,18,10,19,6), 30000000)).isEqualTo(10613991)
        }
    }
})
