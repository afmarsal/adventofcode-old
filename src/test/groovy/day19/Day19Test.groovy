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

    def "test 19.2"() {
        /**

         1,0,0,0,0,0

         0: reg3 = reg3 + 16              // ip = (reg3 + 16) + 1
         1: reg4 = 1                        // ip++
         2: reg2 = 1                        // ip++
         3: reg1 = reg4 * reg2          // ip++
         4: reg1 = reg1 == reg5 ? 1 : 0 // ip++
         5: reg3 = reg1 + reg3          // ip = ip + reg1 + 1
         6: reg3 = reg3 + 1              // ip = ip + 1 <=> ip += 2
         7: reg0 = reg4 + reg0         // ip++
         8: reg2 = reg2 + 1               // ip++
         9: reg1 = (reg2 > reg5)? 1 : 0 // ip++
         10: reg3 = reg3 + reg1        // ip = ip + reg1 + 1
         11: reg3 = 2                      // ip = 2
         12: reg4 = reg1 + reg4        // ip++
         13: reg1 = (reg4 > reg5)? 1 : 0 // ip++
         14: reg3 = reg1 + reg3        // ip = ip + reg1 + 1
         15: reg3 = 1                      // ip = 1
         16: reg3 = reg3 * reg3        // ip = ip * ip + 1
         17: reg5 = reg5 + 2             // ip++
         18: reg5 = reg5 * reg5        // ip++
         19: reg5 = reg3 * reg5        // ip++
         20: reg5 = reg5 * 11            // ip++
         21: reg1 = reg1 + 6             // ip++
         22: reg1 = reg1 * reg3        // ip++
         23: reg1 = reg1 + 13            // ip++
         24: reg5 = reg1 + reg5        // ip++
         25: reg3 = reg3 + reg0        // ip = ip + reg0 + 1
         26: reg3 = 0                      // ip = 0
         27: reg3 = reg1                 // ip = reg1 + 1
         28: reg1 = reg3 * reg1        // ip++
         29: reg1 = reg3 + reg1        // ip = ip + reg0 + 1
         30: reg1 = reg3 * reg1        // ip++
         31: reg1 = reg3 * 14            // ip++
         32: reg1 = reg1 * reg3        // ip++
         33: reg5 = reg1 + reg5        // ip++
         34: reg0 = 0                      // ip++
         35: reg3 = 0                      // ip = 0


         0:
         r3 += 16    // it = 1 -> ip = 17

         17:  // it 0
         r5 += 2     // ip = 18
         r5 *= r5  // ip = 19
         r5 *= r3  // ip = 20
         r5 *= 11    // ip = 21
         r1 += 6     // ip = 22
         r1 *= r3  // ip = 23
         r1 += 13    // ip = 24
         r5 += r1  // ip = 25
         r3 += r0  // ip = ip + r0 + 1

         27: // it 0
         r3 = r1 // ip = 28
         28:
         r1 *= r3     // ip = 29
         r1 += r3     // ip = 30
         r1 *= r3      // ip = 31
         r1 = r3 * 14 // ip = 32
         r1 *= r3
         r5 += r1
         r0 = 0
         r3 = 0      // ip = 0



         */
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

