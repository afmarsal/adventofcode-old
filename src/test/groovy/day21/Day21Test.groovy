package day21

import spock.lang.Specification

class Day21Test extends Specification {

    def "test 21.1"() {
        expect:
            Day21.test1(input, output) == output

        where:
            input        | output
            FINAL_INPUT  | 103548 // r0 must be equal to r4
//            MARCAL  | 5970144  // r0 must be equal to r1
    }

    static final FINAL_INPUT = '''\
#ip 1
seti 123 0 4
bani 4 456 4
eqri 4 72 4
addr 4 1 1
seti 0 0 1
seti 0 2 4
bori 4 65536 3
seti 10552971 1 4
bani 3 255 5
addr 4 5 4
bani 4 16777215 4
muli 4 65899 4
bani 4 16777215 4
gtir 256 3 5
addr 5 1 1
addi 1 1 1
seti 27 7 1
seti 0 1 5
addi 5 1 2
muli 2 256 2
gtrr 2 3 2
addr 2 1 1
addi 1 1 1
seti 25 0 1
addi 5 1 5
seti 17 2 1
setr 5 7 3
seti 7 8 1
eqrr 4 0 5
addr 5 1 1
seti 5 0 1'''
    /*
    f0() {
00:     r4 = 123
01:     r4 = r4 & 456
03-04:  if (r4 != 72) {
05:         f0()
        } else {
            f5()
        }
}

f5() {
05:     r4 = 0
}

f6() {
06:     r3 = r4 | 65536
07:     r4 = 10552971
}

f8() {
08:     r5 = r3 & 255
09:     r4 = r4 + r5
10:     r4 = r4 & 16777215
11:     r4 = r4 * 65899
12:     r4 = r4 & 16777215
13:     r5 = 256 > r3 ? 1 : 0
14:     if (r3 >= 256) {
            if (r4 == r0) {
29:            EXIT!!!
            } else {
30:            f6()
            }
        } else {
15:          f17()
        }
}

f17() {
17:     r5 = 0
        f18()
}

f18() {

18:     r2 = r5 + 1
19:     r2 = r2 * 256
20:     r2 = r2 > r3 ? 1 : 0
20:     if (r2 > r3) {
23 :        //f26()
26 :        r3 = r5
27 :        f8()
        } else {
             //f24()
24:         r5 = r5 + r1 = r5 + 24
25:         f18()
        }
}

     */

    static final MARCAL = '''\
#ip 4
seti 123 0 1
bani 1 456 1
eqri 1 72 1
addr 1 4 4
seti 0 0 4
seti 0 3 1
bori 1 65536 5
seti 8586263 3 1
bani 5 255 2
addr 1 2 1
bani 1 16777215 1
muli 1 65899 1
bani 1 16777215 1
gtir 256 5 2
addr 2 4 4
addi 4 1 4
seti 27 8 4
seti 0 1 2
addi 2 1 3
muli 3 256 3
gtrr 3 5 3
addr 3 4 4
addi 4 1 4
seti 25 8 4
addi 2 1 2
seti 17 7 4
setr 2 0 5
seti 7 8 4
eqrr 1 0 2
addr 2 4 4
seti 5 4 4'''
}
