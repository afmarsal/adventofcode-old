package day16

class Sample {
    def registersBefore = []
    Instruction instruction
    def registersAfter = []


    def matchesMoreThan3AfterRunning() {
        Map results = getResultMap()[0]
        results.any { it.value >= 3 }
    }

    def getResultMap() {
        Map<List, Integer> validOutputCounts = [:]
        List possibleOps = []
        Op.values().each { op ->
            def input = registersBefore.clone()
            def output = op.run(input, instruction)
            if (output == registersAfter) {
                validOutputCounts.merge(output, 1, { v1, v2 -> v1 + v2 })
                possibleOps << op
            }
        }
        [validOutputCounts, instruction.opCode, possibleOps]
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
