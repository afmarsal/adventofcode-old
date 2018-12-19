package day19

class Instruction {

    Op op
    long[] params

    long getInputA() {
        params[0]
    }

    long getInputB() {
        params[1]
    }

    long getOutput() {
        params[2]
    }

    static Instruction from(String line) {
        def matcher = line =~ /(\w+) (\d+) (\d+) (\d+)/
        if (!matcher) throw new RuntimeException("Invalid instruction input: $line")
        def op = Op.valueOf(matcher[0][1])
        def params = [matcher[0][2].toInteger(), matcher[0][3].toInteger(), matcher[0][4].toInteger()]
        new Instruction(op: op, params: params)
    }

    @Override
    public String toString() {
        return "{$op $params}"
    }
}

