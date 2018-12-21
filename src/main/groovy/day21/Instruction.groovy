package day21

class Instruction {

    day21.Op op
    int[] params

    int getInputA() {
        params[0]
    }

    int getInputB() {
        params[1]
    }

    int getOutput() {
        params[2]
    }

    static Instruction from(String line) {
        def matcher = line =~ /(\w+) (\d+) (\d+) (\d+)/
        if (!matcher) throw new RuntimeException("Invalid instruction input: $line")
        def op = day21.Op.valueOf(matcher[0][1])
        def params = [matcher[0][2].toInteger(), matcher[0][3].toInteger(), matcher[0][4].toInteger()]
        new Instruction(op: op, params: params)
    }

    @Override
    public String toString() {
        return "{$op $params}"
    }
}

