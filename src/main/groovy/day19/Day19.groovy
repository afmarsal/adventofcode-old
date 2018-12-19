package day19

class Day19 {

    static def test1(String input) {

        def (int boundRegister, List<Instruction> instructions) = parseInstructions(input)

        def registers = [0L, 0L, 0L, 0L, 0L, 0L]
        execute(registers, boundRegister, instructions)
    }

    static def test2(String input) {
        def (int boundRegister, List<Instruction> instructions) = parseInstructions(input)
//        instructions.each { println it }
        def instructionsCopy = instructions.collect { it }
        def registers = [1L, 0L, 0L, 0L, 0L, 0L]
        def r0 = execute(registers, boundRegister, instructionsCopy)

    }

    private static List parseInstructions(String input) {
        def lines = input.readLines()
        def matcher = lines[0] =~ /#ip (\d+)/
        if (!matcher) throw new RuntimeException("Invalid first line ${lines[0]}")
        int boundRegister = matcher[0][1].toInteger()

        def instructions = lines.drop(1).collect { Instruction.from(it) }
//        instructions.each { println it }
        [boundRegister, instructions]
    }

    private static int execute(def registers, int boundRegister, List<Instruction> instructions, def r5 = null) {

        if (r5 != null) {
//            instructions.add(instructions.size() - 2, Instruction.from("seti $r5 0 5"))
//            println "\nOverride $r5"
//            instructions.each { println it }
        }
        def counter = 0
        while (registers[boundRegister] < instructions.size()) {
            def ip = registers[boundRegister]
            registers = instructions[registers[boundRegister]].op.run(registers, instructions[ip])
            println "$counter> ip: $ip, instruction=${instructions[ip].toString()}, registers=${registers.toString()}"
            ++counter
            ++(registers[boundRegister])
        }
        registers[0]
    }

}

