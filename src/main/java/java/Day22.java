package java;

public class Day22 {

    static int test222(final int depth, final int x, final int y) {

        int maxX = x * 100;
        int maxY = y * 10;

        return 0;
    }

}

class Position {
    int x;
    int y;
}

class Cave<T> {

    T[][] grid;

    static <T> Cave<T> from(final int depth, final int maxX, final int maxY) {
        Cave result = new Cave();
        result.grid = new T[maxX][];
    }

}

class CaveCell {

}