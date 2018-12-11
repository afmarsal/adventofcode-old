import spock.lang.Specification

class Day11Test extends Specification {

    def "cell power"() {

        expect:
            Day11.cellsFor(serial)[cell[0] - 1][cell[1] - 1] == output

        where:
            serial | cell       | output
            8      | [3, 5]     | 4
            57     | [122, 79]  | -5
            71     | [101, 153] | 4
            39     | [217, 196] | 0
    }

    def "test 1: cell grid powers"() {
        expect:
            Day11.maxCellPowerFor(serial) == cell

        where:
            serial | cell      | power
            18     | [33, 45]  | 29
            42     | [21, 61]  | 30
            7139   | [20, 62]  | 30
            5034   | [235, 63] | 29
    }

    def "test 2: cell grid powers with size"() {
        expect:
            Day11.maxCellPowerWithSizeFor(serial) == cell

        where:
            serial | cell           | power
//            18     | [90, 269, 16]  | 113
//            42     | [232, 251, 12] | 119
//            7139   | [229, 61, 16]  | 0
            5034   | [229, 251, 16] | 109
    }
}
