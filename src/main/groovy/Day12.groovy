class Day12 {

    static def test1(final String input, long generations) {

        def potConfigs = [:] as HashMap
        def (String pots, long current0Index, Map<String, String> rules) = parseInput(input)
        long it = 0
        while (++it <= generations) {
            def newPots = new StringBuilder("." * pots.length())
            for (int idx = 2; idx < pots.size() - 2; ++idx) {
                def partToMatch = pots[(idx - 2)..(idx + 2)]
                newPots.setCharAt(idx, rules.getOrDefault(partToMatch, ".").toCharacter())
            }
            (pots, current0Index) = normalizePots(newPots, current0Index)
            def previousIndex = potConfigs.putIfAbsent(pots, current0Index)
//            println "${sprintf("%2d", [it])}> idx: $current0Index, pots: ${pots}"
            if (previousIndex != null) {
                current0Index -= (generations - it)
                break
            }
        }
        sumPots(pots, current0Index)
    }

    static def parseInput(String input) {
        def lines = input.readLines()

        def matcher = lines[0] =~ /initial state: ([#\\.]*)/
        if (!matcher) throw new RuntimeException("Invalid first line")
        def (pots, current0Index) = normalizePots(new StringBuilder(matcher[0][1]), 0)

        Map<String, String> rules = lines.drop(2).collectEntries {
            def m2 = it =~ /([#\\.]{5}) => ([#\\.])/
            if (!m2) throw new RuntimeException("Invalid first line")
            [m2[0][1], m2[0][2]]
        }
        [pots, current0Index, rules]
    }

    static def normalizePots(final StringBuilder sb, long curr0Index) {
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

    private static long sumPots(String pots, long current0Index) {
        long result = 0L
        pots.eachWithIndex { String ch, long i ->
            if (ch == "#") result += (i - current0Index)
        }
        result
    }
}
