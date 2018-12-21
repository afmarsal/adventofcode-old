package day21

enum Op {

    addr({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] + registers[instruction.inputB]
        registers
    }),
    addi({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] + instruction.inputB
        registers
    }),
    mulr({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] * registers[instruction.inputB]
        registers
    }),
    muli({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] * instruction.inputB
        registers
    }),
    banr({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] & registers[instruction.inputB]
        registers
    }),
    bani({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] & instruction.inputB
        registers
    }),
    borr({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] | registers[instruction.inputB]
        registers
    }),
    bori({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] | instruction.inputB
        registers
    }),
    setr({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA]
        registers
    }),
    seti({ def registers, Instruction instruction ->
        registers[instruction.output] = instruction.inputA
        registers
    }),
    gtir({ def registers, Instruction instruction ->
        registers[instruction.output] = instruction.inputA > registers[instruction.inputB] ? 1 : 0
        registers
    }),
    gtri({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] > instruction.inputB ? 1 : 0
        registers
    }),
    gtrr({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] > registers[instruction.inputB] ? 1 : 0
        registers
    }),
    eqir({ def registers, Instruction instruction ->
        registers[instruction.output] = instruction.inputA == registers[instruction.inputB] ? 1 : 0
        registers
    }),
    eqri({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] == instruction.inputB ? 1 : 0
        registers
    }),
    eqrr({ def registers, Instruction instruction ->
        registers[instruction.output] = registers[instruction.inputA] == registers[instruction.inputB] ? 1 : 0
        registers
    });

    Closure operation

    Op(Closure operation) {
        this.operation = operation
    }

    def run(def registers, Instruction instruction) {
        operation.call(registers, instruction)
    }

}
