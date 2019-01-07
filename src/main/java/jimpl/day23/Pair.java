package jimpl.day23;

class Pair<FIRST, SECOND> {

    private final FIRST first;
    private final SECOND second;

    private Pair(final FIRST first, final SECOND second) {
        this.first = first;
        this.second = second;
    }

//    public static <FIRST> FIRST getFirst(Pair<FIRST, ?> pair) {
//        return pair.getFirst();
//    }

    public FIRST getFirst() {
        return first;
    }

    public SECOND getSecond() {
        return second;
    }

    static <FIRST extends Comparable<FIRST>, SECOND> Pair<FIRST, SECOND> pairOf(FIRST first, SECOND second) {
        return new Pair<>(first, second);
    }

}
