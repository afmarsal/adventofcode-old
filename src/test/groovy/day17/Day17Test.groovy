package day17

import spock.lang.Specification

class Day17Test extends Specification {

    def test1() {
        expect:
            Day17.test1(input) == output

        where:
            input          | output
            SAMPLE_INPUT_1 | 57
//            FINAL_INPUT  | 502

    }

    static final SAMPLE_INPUT_1 = '''\
x=495, y=2..7
y=7, x=495..501
x=501, y=3..7
x=498, y=2..4
x=506, y=1..2
x=498, y=10..13
x=504, y=10..13
y=13, x=498..504'''
}
