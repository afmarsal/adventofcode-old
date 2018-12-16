package day16

class Instruction {

    int[] instruction

    int getOp() {
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


    @Override
    public String toString() {
        return "Instruction{Op:$op, A: $inputA, B: $inputB, C: $output}"
    }
}

