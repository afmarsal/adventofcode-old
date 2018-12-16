package day15

import static day15.PointType.OPEN
import static day15.Position.posOf

class Square {

    PointType pointType
    Position position
    Player player

    boolean isFree() {
        pointType == OPEN && !player
    }

    static Square from(def ch, int x, int y, Player player) {
        new Square(pointType: PointType.of(ch), position: posOf(x, y), player: player)
    }


    @Override
    public String toString() {
        return "Square{" +
                "pointType=" + pointType +
                ", position=" + position +
                ", player=" + (player ? "yes" : "no") +
                '}';
    }
}
