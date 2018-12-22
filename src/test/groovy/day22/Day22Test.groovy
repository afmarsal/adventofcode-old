package day22


import spock.lang.Specification

class Day22Test extends Specification {

    def "test 22.1"() {
        expect:
            Day22.test1(depth, target) == output

        where:
            depth | target   | output
//            510   | [10, 10] | 114
//            11109 | [9,731] | 7299
            8103 | [9,758] | 7743
    }

}
