package day21

class Day21 {

    static def test1(String input, int r0) {

        def lines = input.readLines()
        def matcher = lines[0] =~ /#ip (\d+)/
        if (!matcher) throw new RuntimeException("Invalid first line ${lines[0]}")
        int boundRegister = matcher[0][1].toInteger()

        def instructions = lines.drop(1).collect { Instruction.from(it) }
//        instructions.each { println it }

        int[] registers = [r0, 0, 0, 0, 0, 0]

        def counter = 0
        while (registers[boundRegister] < instructions.size()) {

            def ip = registers[boundRegister]
            registers = instructions[registers[boundRegister]].op.run(registers, instructions[ip])

            if (counter % 100_000 == 0) {
                println "$counter> ip: $ip, instruction=${instructions[ip].toString()}, registers=${registers.toString()}"
            }

//            if (counter == 10000) break
            ++counter
            ++(registers[boundRegister])
        }
        registers[0]
    }

    static def test2(String input, int r0) {

        def lines = input.readLines()
        def matcher = lines[0] =~ /#ip (\d+)/
        if (!matcher) throw new RuntimeException("Invalid first line ${lines[0]}")
        int boundRegister = matcher[0][1].toInteger()

        def instructions = lines.drop(1).collect { day21.Instruction.from(it) }
//        instructions.each { println it }

        int[] registers = [0, 0, 0, 0, 0, 0]

        def counter = 0
        def oldR4 = 0
        def previousValues = [] as Set
        while (registers[boundRegister] < instructions.size()) {

            def ip = registers[boundRegister]
            registers = instructions[registers[boundRegister]].op.run(registers, instructions[ip])

//            println "$counter> ip: $ip, instruction=${instructions[ip].toString()}, registers=${registers.toString()}"
            if (counter > 10) {
//                def registersStr = registers.join[","]
//                if (ip == 28 && registersStr in previousValues) {
//                    println "$counter> ip: $ip, instruction=${instructions[ip].toString()}, registers=${registers.toString()}"
//                    break
//                }

                oldR4 = registers[4]
            }
            if (counter % 100_000 == 0) {
                println "$counter> ip: $ip, instruction=${instructions[ip].toString()}, registers=${registers.toString()}"
            }

//            if (counter == 10000) break
            ++counter
            ++(registers[boundRegister])
        }
        registers[0]
    }

}

