package day15

enum PointType {
    WALL, OPEN

    static PointType of(String c) {
        c == '#' ? WALL : OPEN
    }

    @Override
    String toString() {
        this == WALL ? '#' : '.'
    }
}
