package day19

class Day19 {

    /*
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
seti 0 3 3
     */
    static def test1(String input) {

        def lines = input.readLines()
        def matcher = lines[0] =~ /#ip (\d+)/
        if (!matcher) throw new RuntimeException("Invalid first line ${lines[0]}")
        int boundRegister = matcher[0][1].toInteger()

        def instructions = lines.drop(1).collect { Instruction.from(it) }
        instructions.each { println it }

        def registers = [1L, 0L, 0L, 0L, 0L, 0L]

        def counter = 0, printing = false, printed = 0
        def stack = [] as LinkedList
        def old0 = 1, finishIn10 = false, counter10 = 0
        while (registers[boundRegister] < instructions.size()) {
            def ip = registers[boundRegister]
            registers = instructions[registers[boundRegister]].op.run(registers, instructions[ip])
//            if (counter % 100_000 == 0) {
//                printing = true
//            }
//            if (printing) {
//                println "$counter> ip: $ip, instruction=${instructions[ip]}, registers=${registers}"
//                if (++printed == 20) {
//                    printing = false
//                    printed = 0
//                }
//            }
//            stack.addLast "$counter> ip: $ip, instruction=${instructions[ip].toString()}, registers=${registers.toString()}"
//            if (old0 != registers[0]) {
//                println "Detected change in register[0] at $counter: $old0 vs ${registers[0]}"
//                finishIn10 = true
//                counter10 = counter
//            }
//            old0 = registers[0]
//            if (finishIn10 && (counter == counter10 + 10)) {
//                println "Printing stack at $counter (started at $counter10)"
//                finishIn10 = false
//                stack.each { println it }
//                println ""
//            }
//            if (stack.size() > 20) stack.removeFirst()
            println "$counter> ip: $ip, instruction=${instructions[ip].toString()}, registers=${registers.toString()}"
            if (counter == 40) break
            ++counter
            ++(registers[boundRegister])
        }
        registers[0]
    }

}

