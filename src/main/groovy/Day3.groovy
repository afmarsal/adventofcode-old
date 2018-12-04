import java.util.regex.Matcher

import static Day3.Status.MORE_THAN_ONE
import static Day3.Status.ONE

class Day3 {

    static final SIZE = 1000
    public static final String CLAIM_PATTERN = /#(\d+) @ (\d+),(\d+): (\d+)x(\d+)/

    static def test1(String input) {
        Status[][] inches = new Status[SIZE][SIZE]
        int duplicatedInches = 0
        for (String line : input.readLines()) {
            def m = line =~ CLAIM_PATTERN
            if (!m) throw new RuntimeException("Invalid line ${line}")
            Claim.from(m).with {
                x.upto(x + w - 1, { i ->
                    y.upto(y + h - 1, { j ->
                        if (inches[i][j] == null) {
                            inches[i][j] = ONE
                        } else if (inches[i][j] == ONE) {
                            inches[i][j] = MORE_THAN_ONE
                            ++duplicatedInches
                        }
                    })
                })
            }
        }
        duplicatedInches
    }

    static def test2(String input) {
        Integer[][] inches = new Integer[SIZE][SIZE]
        Set<Integer> discarded = []
        Set<Integer> unique = []
        for (String line : input.readLines()) {
            def m = line =~ CLAIM_PATTERN
            if (!m) throw new RuntimeException("Invalid line ${line}")
            Claim.from(m).with {
                x.upto(x + w - 1, { i ->
                    y.upto(y + h - 1, { j ->
                        if (inches[i][j] == null) {
                            inches[i][j] = id
                            if (! (id in discarded)) {
                                unique.add id
                            }
                        } else {
                            unique.remove id
                            unique.remove inches[i][j]
                            discarded.add id
                            discarded.add inches[i][j]
                        }
                    })
                })
            }
        }
        assert unique.size() == 1
        unique.find {true}
    }

    static class Claim {
        int id
        int x
        int y
        int w
        int h

        static Claim from(Matcher m) {
            def result = new Claim()
            result.id = m.group(1).toInteger()
            result.x = m.group(2).toInteger()
            result.y = m.group(3).toInteger()
            result.w = m.group(4).toInteger()
            result.h = m.group(5).toInteger()
            result
        }


        @Override
        String toString() {
            final StringBuilder sb = new StringBuilder("Claim{");
            sb.append("id=").append(id);
            sb.append(", x=").append(x);
            sb.append(", y=").append(y);
            sb.append(", w=").append(w);
            sb.append(", h=").append(h);
            sb.append('}');
            return sb.toString();
        }
    }

    enum Status {
        ONE, MORE_THAN_ONE
    }
}
