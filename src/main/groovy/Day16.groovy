import day16.Instruction
import day16.Sample

class Day16 {

    static def test1(String input) {
        def lines = input.readLines()
        List<Sample> samples = parseLines(lines)

        println "Read ${samples.size()} samples"

        samples.findAll { it.matchesMoreThan3AfterRunning() }.size()
    }

    private static List<Sample> parseLines(List<String> lines) {
        List<Sample> samples = parseSamples(lines)
    }

    private static List<Sample> parseSamples(List<String> lines) {
        def samples = []
        def i = 0
        while (true) {
            Sample sample = new Sample()
            sample.registersBefore = parseRegisters(lines[i++])
            sample.instruction = parseInstruction(lines[i++])
            sample.registersAfter = parseRegisters(lines[i++])
            samples << sample
            if (lines[i].trim().empty && lines[i + 1].trim().empty) break
            // Blank line
            ++i
        }
        i++
        samples
    }

    private static def parseRegisters(String line) {
        def matcher = line =~ /[^\[]\[(\d+), (\d+), (\d+), (\d+)\]/
        if (!matcher) throw new RuntimeException("Invalid register input: $line")
        [matcher[0][1].toInteger(), matcher[0][2].toInteger(), matcher[0][3].toInteger(), matcher[0][4].toInteger()]
    }

    private static Instruction parseInstruction(String line) {
        def matcher = line =~ /(\d+) (\d+) (\d+) (\d+)/
        if (!matcher) throw new RuntimeException("Invalid instruction input: $line")
        def values = [matcher[0][1].toInteger(), matcher[0][2].toInteger(), matcher[0][3].toInteger(), matcher[0][4].toInteger()]
        new Instruction(instruction: values)
    }

}
