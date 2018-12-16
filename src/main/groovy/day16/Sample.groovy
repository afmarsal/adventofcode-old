package day16

class Sample {
    def registersBefore = []
    Instruction instruction
    def registersAfter = []


    def matchesMoreThan3AfterRunning() {
        Map<int[], Integer> results = [:]
        Op.values().each { op ->
            def input = registersBefore.clone()
            def output = op.run(input, instruction)
            if (output == registersAfter) {
                results.merge(output, 1, {v1, v2 -> v1 + v2})
            }
        }
        results.any { it.value >= 3 }
    }


    @Override
    public String toString() {
        return "Sample{" +
                "registersBefore=" + registersBefore +
                ", instruction=" + instruction +
                ", registersAfter=" + registersAfter +
                '}';
    }
}
