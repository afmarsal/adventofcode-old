class Day5 {

    static final Character CASING_DIFF = ('a' as char) - ('A' as char)

    static def test1(String input) {
        def result = new StringBuilder(input)
        int i = 0
        while (i < result.size() - 1) {
            def current = result[i].toCharacter()
            def next = result[i + 1].toCharacter()
            if (Math.abs(current - next) == CASING_DIFF) {
                result.delete(i, i + 2)
                if (i > 0) --i
            } else {
                ++i
            }
        }
        result.size()
    }

    static def test2(String input) {
        def pairs = ['A'..'Z', 'a'..'z'].transpose().collect { it.join() }
        pairs.collect { test1(input.replaceAll("[$it]", "")) }.min()
    }

}
