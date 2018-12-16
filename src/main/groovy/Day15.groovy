import day15.Board
import day15.Direction
import day15.Player

import static day15.Logger.log

class Day15 {

    static int test1(String input) {

        Board board = Board.fromInput(input)
        println "Round 0"
        board.printStatus()

        int round = 0
        rounds:
        while (true) {
            ++round
            log "\n======== Round $round"
            def playerIterator = board.playerIterator()
//            board.printStatus()
            while (playerIterator.hasNext()) {
                Player player = playerIterator.next()
//                log "\n****** Turn for player: $player"
                def enemies = board.enemiesFor(player)
                if (enemies.empty) {
                    break rounds
                }
                if (!player.canAttackAny(enemies)) {
//                    log "Moving phase"
                    // Move phase
                    def candidatePositions = enemies.collectMany { enemy ->
                        board.freeAdjacentPosFor(enemy.position)
                    }.sort()

                    Direction direction = board.bestDirection(player.position, candidatePositions)
                    if (!direction) {
                        continue
                    }
                    board.movePlayer(player, direction)
//                    board.printStatus()
                } else {
//                    log "Skipping moving phase"
                }

                if (player.canAttackAny(enemies)) {
//                    log "Attack phase"
                    def selectedEnemy = player.selectBestEnemy(enemies)
//                    log "Best enemy: $selectedEnemy"
                    board.attack(player, selectedEnemy)
                } else {
//                    log "Skipping attack phase"
                }
            }
            println "End round $round"
            board.printStatus()
        }
        log "Alive players at round $round: "
        board.players.findAll { !it.dead }.each { log it }
        board.players.sum { it.dead ? 0 : it.hitPoints } * (round - 1)
    }

}
