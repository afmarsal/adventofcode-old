package util

import static util.Direction.*

class Position implements Comparable<Position> {

    int row
    int col

    static Position posOf(int row, int col) {
        new Position(row: row, col: col)
    }

    Position offsetX(int offsetX) {
        posOf(row + offsetX, col)
    }

    Position offsetY(int offsetY) {
        posOf(row, col + offsetY)
    }

    Position next(Direction direction) {
        switch (direction) {
        case UP: return posOf(row - 1, col)
        case DOWN: return posOf(row + 1, col)
        case LEFT: return posOf(row, col - 1)
        case RIGHT: return posOf(row, col + 1)
        }
    }

    @Override
    int compareTo(Position o) {
        col == o.col ? Integer.compare(row, o.row) : Integer.compare(col, o.col)
    }

    @Override
    String toString() {
        return "$row,$col"
    }

    boolean equals(final o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Position position = (Position) o

        if (col != position.col) return false
        if (row != position.row) return false

        return true
    }

    int hashCode() {
        int result
        result = row
        result = 31 * result + col
        return result
    }
}
