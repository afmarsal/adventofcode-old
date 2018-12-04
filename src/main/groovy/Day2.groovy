class Day2 {

    static def test1(String input) {
        def map = input.inject([:]) { map, ch ->
            map.merge(ch, 1, { v1, v2 -> v1 + v2 })
            map
        }
        return [hasNOccurrences(map, 2) ? 1 : 0, hasNOccurrences(map, 3) ? 1 : 0]
    }

    static int test1checksum(final String input) {
        def acc = input.readLines()
                .collect { test1(it) }
                .inject([0, 0]) { l, p ->
            l[0] += p[0]
            l[1] += p[1]
            l
        }
        acc[0] * acc[1]
    }

    private static boolean hasNOccurrences(map, occurrences) {
        map.any { _, v -> v == occurrences }
    }

    static String test2(final String input) {
        def lines = input.readLines()
        for (int i  = 0; i < lines.size(); ++i) {
            for (int j = i + 1; j < lines.size(); ++j) {
                def (common, notMatching) = intersect(lines[i], lines[j])
                if (notMatching.size() == 1) {
                    println "${lines[i]} .. ${lines[j]}"
                    return common
                }
            }
        }
        null
    }

    static def intersect(String s1, String s2) {
        assert s1.size() == s2.size()
        def result = ""
        def notMatching = ""
        for (int i = 0; i < s1.size(); ++i) {
            if (s1[i] == s2[i]) {
                result += s1[i]
            } else {
                notMatching += s1[i]
            }
        }
        [result, notMatching]
    }
}
