class Day1 {

    static void main(String... args) {
    }

    static def test1(String input) {
        return input.readLines().sum { it.toInteger() }
    }

    static def test2(String input) {
        def counter = 0
        def frequencies = [counter] as Set
        while (true) {
            for (String line : input.readLines()) {
                def integer = line.toInteger()
                counter += integer
                if (!frequencies.add(counter)) {
                    return counter
                }
            }
        }
    }
}
