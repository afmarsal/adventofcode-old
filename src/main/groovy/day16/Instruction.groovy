package day16

class Instruction {

    int[] instruction

    int getOpCode() {
        instruction[0]
    }

    int getInputA() {
        instruction[1]
    }

    int getInputB() {
        instruction[2]
    }

    int getOutput() {
        instruction[3]
    }

    static Instruction from(String line) {
        def matcher = line =~ /(\d+) (\d+) (\d+) (\d+)/
        if (!matcher) throw new RuntimeException("Invalid instruction input: $line")
        def values = [matcher[0][1].toInteger(), matcher[0][2].toInteger(), matcher[0][3].toInteger(), matcher[0][4].toInteger()]
        new Instruction(instruction: values)
    }


    @Override
    public String toString() {
        return "Instruction{Op:$opCode, A: $inputA, B: $inputB, C: $output}"
    }
}

