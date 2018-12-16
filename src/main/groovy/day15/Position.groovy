package day15

class Position implements Comparable<Position> {

    int x
    int y

    static Position posOf(int x, int y) {
        new Position(x: x, y: y)
    }

    Position offsetX(int offsetX) {
        posOf(this.x + offsetX, this.y)
    }

    Position offsetY(int offsetY) {
        posOf(this.x, this.y + offsetY)
    }

    @Override
    int compareTo(Position o) {
        y == o.y ? Integer.compare(x, o.x) : Integer.compare(y, o.y)
    }

    @Override
    String toString() {
        return "$x,$y"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Position position = (Position) o

        if (x != position.x) return false
        if (y != position.y) return false

        return true
    }

    int hashCode() {
        int result
        result = x
        result = 31 * result + y
        return result
    }
}
