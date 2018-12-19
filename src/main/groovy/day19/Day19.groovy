package day19

class Day19 {

    static Random random = new Random()

    static def test1(String input) {

        def (int boundRegister, List<Instruction> instructions) = parseInstructions(input)

        def registers = [0L, 0L, 0L, 0L, 0L, 0L]
        execute(registers, boundRegister, instructions)
    }

    static def test2() {
        seti 26 200 5

        def registers = [0L, 0L, 0L, 0L, 0L, 0L]
//        execute(registers, boundRegister, instructions)
//
//        execute(boundRegister, instructions)
    }

    private static List parseInstructions(String input) {
        def lines = input.readLines()
        def matcher = lines[0] =~ /#ip (\d+)/
        if (!matcher) throw new RuntimeException("Invalid first line ${lines[0]}")
        int boundRegister = matcher[0][1].toInteger()

        def instructions = lines.drop(1).collect { Instruction.from(it) }
        instructions.each { println it }
        [boundRegister, instructions]
    }

    private static int execute(def registers, int boundRegister, List<Instruction> instructions) {

        def counter = 0
        while (registers[boundRegister] < instructions.size()) {
            def ip = registers[boundRegister]
            registers = instructions[registers[boundRegister]].op.run(registers, instructions[ip])
//            println "$counter> ip: $ip, instruction=${instructions[ip].toString()}, registers=${registers.toString()}"
            ++counter
            ++(registers[boundRegister])
        }
        registers[0]
    }

}

