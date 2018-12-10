import spock.lang.Specification

class Day9Test extends Specification {

    def "test 1"() {

        expect:
            Day9.test1(players, lastMarble) == output

        where:
            players | lastMarble | output
            5       | 25         | 23 + 9
            10      | 1618       | 8317
            13      | 7999       | 146373
            17      | 1104       | 2764
            21      | 6111       | 54718
            30      | 5807       | 37305
            423     | 71944      | 418237
            423     | 7194400    | 3505711612
    }
}
