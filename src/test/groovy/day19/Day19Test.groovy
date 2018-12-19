package day19


import spock.lang.Specification

class Day19Test extends Specification {

    def "test 19.1"() {
        expect:
            Day19.test1(input) == output

        where:
            input          | output
//            SAMPLE_INPUT_1 | 6
//            SAMPLE_INPUT_2
            FINAL_INPUT    | 1430
//            MARCAL   | 2040
    }

    /**
     * Final input looks like this in pseudo code
     main:
     f17()

     def f01-02() {
     01: r4 = 1
     f2()
     }

     def f2() {
     02: r2 = 1
     f03-f11()
     }
     def f03-13() {}
     03: r1 = r4 * r2 // r4 = 1 => r1 = r2
     04: r1 = (r1 == r5) ? 1 : 0
     if (r5 == r1) {
     07:     r0 = r4 + r0
     }
     08: r2 = r2 + 1
     09: r1 = r2 > r5 ? 1 : 0
     if (r2 <= r5) {
     11:    f03-11()
     } else {
     12:    r4 = r4 + 1
     13:    r1 = (r4 > r5) ? 1 : 0
     14:    if (r4 <= r5) {
     15:       f01-02()
     } else {
     16:        EXIT!!!
     }
     }
     }
        r0 accumulates ALL the divisors of r5
     * @return
     */
    def "test 19.2"() {
        expect:
            Day19.test2(input) == output

        where:
            input          | output
//            SAMPLE_INPUT_1 | 6
//            SAMPLE_INPUT_2
            FINAL_INPUT    | 14266944
//            MARCAL   | 25165632
    }

    static final SAMPLE_INPUT_1 = '''\
#ip 0
seti 5 0 1
seti 6 0 2
addi 0 1 0
addr 1 2 3
setr 1 0 0
seti 8 0 4
seti 9 0 5'''

    static final FINAL_INPUT = '''\
#ip 3
addi 3 16 3
seti 1 0 4
seti 1 7 2
mulr 4 2 1
eqrr 1 5 1
addr 1 3 3
addi 3 1 3
addr 4 0 0
addi 2 1 2
gtrr 2 5 1
addr 3 1 3
seti 2 6 3
addi 4 1 4
gtrr 4 5 1
addr 1 3 3
seti 1 3 3
mulr 3 3 3
addi 5 2 5
mulr 5 5 5
mulr 3 5 5
muli 5 11 5
addi 1 6 1
mulr 1 3 1
addi 1 13 1
addr 5 1 5
addr 3 0 3
seti 0 6 3
setr 3 1 1
mulr 1 3 1
addr 3 1 1
mulr 3 1 1
muli 1 14 1
mulr 1 3 1
addr 5 1 5
seti 0 0 0
seti 0 3 3'''

    static final MARCAL = '''\
#ip 1
addi 1 16 1
seti 1 4 2
seti 1 0 3
mulr 2 3 4
eqrr 4 5 4
addr 4 1 1
addi 1 1 1
addr 2 0 0
addi 3 1 3
gtrr 3 5 4
addr 1 4 1
seti 2 4 1
addi 2 1 2
gtrr 2 5 4
addr 4 1 1
seti 1 1 1
mulr 1 1 1
addi 5 2 5
mulr 5 5 5
mulr 1 5 5
muli 5 11 5
addi 4 2 4
mulr 4 1 4
addi 4 16 4
addr 5 4 5
addr 1 0 1
seti 0 7 1
setr 1 5 4
mulr 4 1 4
addr 1 4 4
mulr 1 4 4
muli 4 14 4
mulr 4 1 4
addr 5 4 5
seti 0 9 0
seti 0 4 1'''

}

