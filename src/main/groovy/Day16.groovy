import day16.Instruction
import day16.Op
import day16.Sample

class Day16 {

    static def test1(String input) {
        def lines = input.readLines()
        def (samples, __) = parseLines(lines)

        println "Read ${samples.size()} samples"

        samples.findAll { it.matchesMoreThan3AfterRunning() }.size()
    }

    static def test2(String input) {
        def lines = input.readLines()
        def (samples, instructions) = parseLines(lines)

        Map<Integer, List<Op>> possibleOps = buildPossibleOpCodesMap(samples)
        def codeToOps = workOutOpCodes(possibleOps)

        def registers = [0, 0, 0, 0]
        instructions.each {
            registers = codeToOps[it.opCode].run(registers, it)
        }
        registers[0]
    }

    private static Map<Integer, List<Op>> buildPossibleOpCodesMap(List<Sample> samples) {
        Map<Integer, List<Op>> possibleOps = [:]
        samples.each {

            def (Map __, int opCode, List<Op> ops) = it.resultMap
            if (opCode in possibleOps) {
                print "Merging into op $opCode -> ${possibleOps[opCode]} ^ $ops = "
                possibleOps[opCode] = possibleOps[opCode].intersect(ops)
                println possibleOps[opCode]
            } else {
                println "Adding for the 1st op: $opCode -> $ops"
                possibleOps[opCode] = ops
            }
        }
        println "Possibilities: "
        possibleOps.each { k, v ->
            println "$k -> $v"
        }

        possibleOps
    }

    private static def parseLines(List<String> lines) {
        def (samples, idx) = parseSamples(lines)
        while (lines[++idx].trim().empty){}
        def instructions = parseInstructions(lines.subList(idx, lines.size()))
        [samples, instructions]
    }


    private static List<Instruction> parseInstructions(List<String> lines) {
        lines.collect { Instruction.from(it) }
    }

    private static def parseSamples(List<String> lines) {
        def samples = []
        def i = 0
        while (true) {
            Sample sample = new Sample()
            sample.registersBefore = parseRegisters(lines[i++])
            sample.instruction = Instruction.from(lines[i++])
            sample.registersAfter = parseRegisters(lines[i++])
            samples << sample
            if (lines[i].trim().empty && lines[i + 1].trim().empty) break
            // Blank line
            ++i
        }
        [samples, i]
    }

    private static def parseRegisters(String line) {
        def matcher = line =~ /[^\[]\[(\d+), (\d+), (\d+), (\d+)\]/
        if (!matcher) throw new RuntimeException("Invalid register input: $line")
        [matcher[0][1].toInteger(), matcher[0][2].toInteger(), matcher[0][3].toInteger(), matcher[0][4].toInteger()]
    }

    private static Map<Integer, Op> workOutOpCodes(def possibleOps) {
        // Reduce until all op codes have only 1 candidate
        def workedOutCodes = [:]
        while (workedOutCodes.size() < 16) {
            possibleOps.each { k, v ->
                println "$k -> $v"
            }
            def newWorkedOutOps = []
            possibleOps
                    .findAll { it.value.size() == 1 }
                    .each { e ->
                workedOutCodes[e.key] = e.value.first()
                newWorkedOutOps << e.value.first()
            }
            println "New worked out ops: $newWorkedOutOps"
            possibleOps.each {
                possibleOps[it.key].removeAll(newWorkedOutOps)
            }
            println "After iteration"
            possibleOps.each { k, v ->
                println "$k -> $v"
            }
        }
        println "Final result"
        workedOutCodes.each { k, v ->
            println "$k -> $v"
        }
        workedOutCodes
    }
}
