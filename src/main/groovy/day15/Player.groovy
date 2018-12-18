package day15

import util.Direction
import util.Position

import static util.Direction.*
import static util.Position.posOf

class Player {
    Team team
    Position position
    int hitPoints = 200

    int getX() {
        position.x
    }

    int getY() {
        position.y
    }

    def attacked() {
        hitPoints -=3
//        log "${this} has been attacked"
    }

    def move(Direction direction) {
        switch (direction) {
            case UP:
                --position.y
                break
            case DOWN:
                ++position.y
                break
            case LEFT:
                --position.x
                break
            case RIGHT:
                ++position.x
                break
        }
    }

    boolean canAttackAny(def enemies) {
        def enemyPositions = enemies.collect { it.position }
        adjacent.intersect(enemyPositions)
    }

    Player selectBestEnemy(Collection<Player> enemies) {
        def adjacentEnemies = enemies.findAll { it.position in adjacent }
        int minHitPoints = adjacentEnemies.min { it.hitPoints }.hitPoints
        adjacentEnemies
                .findAll { it.hitPoints == minHitPoints }
                .sort { it.position }
                .first()
    }

    private Set<Position> getAdjacent() {
        [position.offsetX(+1), position.offsetX(-1),
         position.offsetY(+1), position.offsetY(-1)] as Set
    }

    static Player from(def ch, int x, int y) {
        Team.from(ch).map {
            new Player(team: it, position: posOf(x, y))
        }.orElse(null)
    }

    boolean isDead() {
        hitPoints <= 0
    }

    @Override
    String toString() {
        return "Player{" +
                "team=" + team +
                ", position=" + position +
                ", points=" + hitPoints +
                '}';
    }
}
