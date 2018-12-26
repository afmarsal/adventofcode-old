package day22;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static java.Day22.test222;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
class Day22JavaTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {510, 10, 10, 45},
                {11109, 9, 731, 1008},
                {8103, 9, 758, 1029}
        });
    }

    private final int depth;
    private final int x;
    private final int y;
    private final int output;

    Day22JavaTest(final int depth, final int x, final int y, final int output) {
        this.depth = depth;
        this.x = x;
        this.y = y;
        this.output = output;
    }

    @Test
    void test22() {
        assertEquals(output, test222(depth, x, y));
    }

}