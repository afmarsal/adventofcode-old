package day15

import util.Direction
import util.Position

class Step {

    Position position
    Direction direction
    Step previous = null
    int distance = 0
    boolean isFirst = false

    int getX() {
        position.x
    }

    int getY() {
        position.y
    }

    static Step stepOf(Position position, Direction direction) {
        new Step(position: position, direction: direction)
    }


    @Override
    public String toString() {
        return "Step{" +
                "position=" + position +
                ", direction=" + direction +
                ", previous=" + previous +
                ", distance=" + distance +
                ", isFirst=" + isFirst +
                '}';
    }
}
