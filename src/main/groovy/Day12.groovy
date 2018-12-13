class Day12 {

`    static final long GENERATIONS = 20
//    static final long GENERATIONS = 20000
//    static final long GENERATIONS = 50000000000
    static final int RULE_SIZE = 4
    static final int OFFSET = RULE_SIZE
    static final String PRE_AND_SUFFIX = "." * OFFSET

    static def test1(final String input) {

        def lines = input.readLines()

        def matcher = lines[0] =~ /initial state: ([#\\.]*)/
        if (!matcher) throw new RuntimeException("Invalid first line")
        String pots
        int dummy
        int current0Index
        (pots, current0Index) = normalizePots(new StringBuilder(matcher[0][1]), dummy)

        Map<String, String> rules = lines.drop(2).collectEntries {
            def m2 = it =~ /([#\\.]{5}) => ([#\\.])/
            if (!m2) throw new RuntimeException("Invalid first line")
            [m2[0][1], m2[0][2]]
        }

        long it = 0
        while (++it <= GENERATIONS) {
            if (it % 100_000 == 0) println "Processing $it..."
            def newPots = new StringBuilder("." * pots.length())
            for (int idx = 2; idx < pots.size() - 2; ++idx) {
                def partToMatch = pots[(idx - 2)..(idx + 2)]
                newPots.setCharAt(idx, rules.getOrDefault(partToMatch, ".").toCharacter())
            }
            (pots, current0Index) = normalizePots(newPots, current0Index)
//            println "${sprintf("%2d", [it])}> idx: $current0Index, pots:${" " * (10 - current0Index)} ${pots}"
        }
//        println pots
        long result = 0L
        pots.eachWithIndex { String ch, int i ->
            if (ch == "#") result += (i - current0Index)
        }
        result
    }

    static def normalizePots(final StringBuilder sb, int curr0Index) {
        while (sb[0] == '.') {
            sb.deleteCharAt(0)
            --curr0Index
        }
        sb.insert(0, "....")
        curr0Index += 4
        while (sb[sb.length() - 1] == ".") sb.deleteCharAt(sb.length() - 1)
        sb.append("....")
        [sb.toString(), curr0Index]
    }
}