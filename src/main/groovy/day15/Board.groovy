package day15

import static day15.Direction.*
import static day15.Logger.log
import static day15.Step.stepOf

class Board {

    Square[][] map
    List<Player> players = [] as LinkedList
    private int playerIdx

    def printStatus() {
        StringBuilder sb = new StringBuilder()
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map.length; x++) {
                sb.append(charToPrintAt(x, y))
            }
            sb.append("\n")
        }
        log sb.toString()
    }

    Iterator<Player> playerIterator() {
        playerIdx = 0
        players.sort { it.position }
        new Iterator<Player>() {
            @Override
            boolean hasNext() {
                return playerIdx < players.size()
            }

            @Override
            Player next() {
                return players[playerIdx++]
            }
        }
    }

    List<Player> enemiesFor(Player player) {
        players.findAll { it.team != player.team }
    }

    List<Position> freeAdjacentPosFor(Position position) {
        possibleStepsFrom(stepOf(position, null), []).collect { it.position }
    }

    boolean isCase(Position position) {
        position.x in 0..map.length && position.y in 0..map.length
    }

    Square getAt(Position position) {
        map[position.x][position.y]
    }

    private def charToPrintAt(int x, y) {
        map[x][y].player ? map[x][y].player.team : map[x][y].pointType
    }

    static Board fromInput(String input) {
        def lines = input.readLines()
        def size = lines.size()
        Square[][] map = new Square[size]
        size.times { map[it] = new Square[size] }
        List<Player> players = [] as LinkedList

        lines.eachWithIndex { String line, int y ->
            line.trim().eachWithIndex { String ch, int x ->
                def player = Player.from(ch, x, y)
                map[x][y] = Square.from(ch, x, y, player)
                if (player) players << player
            }
        }
        new Board(map: map, players: players)
    }

    Direction bestDirection(Position from, Collection<Position> possibleDestinations) {

        def firstSteps = possibleStepsFrom(stepOf(from, null), [])
        firstSteps.each { it.isFirst = true }

        def alreadyVisited = [from] as Set
        nextStepsInRoute(firstSteps, possibleDestinations, alreadyVisited)?.direction
    }

    private Route nextStepsInRoute(List<Step> steps,
                                   Collection<Position> possibleDestinations,
                                   Collection<Position> alreadyVisited) {

        List<Step> nextSteps = []
        List<Step> finalSteps = []
        for (Step step : steps) {
            if (step.position in possibleDestinations) {
                finalSteps << step
                continue
            }
            def nextStepsFromCurrent = possibleStepsFrom(step, alreadyVisited)
            nextSteps.addAll(nextStepsFromCurrent)
            alreadyVisited.addAll(nextSteps.collect { it.position })
        }
        if (!finalSteps.empty) {
            // There's a tie for several target positions. Choose the first one in reading order
            def chosenStep = finalSteps.min { it.position }
            return getRouteFor(chosenStep)
        }
        if (nextSteps.empty) {
            log "Could not find next steps"
            return null
        }
        nextStepsInRoute(nextSteps, possibleDestinations, alreadyVisited)
    }

    private Route getRouteFor(Step step) {
        def directions = []
        Step current = step
        directions << current.direction
        int preventInfinite = 0
        while (!current.isFirst && preventInfinite++ < 2000) {
            current = current.previous
            directions << current.direction
        }
        if (preventInfinite >= 2000) {
            throw new RuntimeException("Something wen wrong")
        }
        directions.reverse(true)
        log "Found route (distance: ${step.distance}: ${directions.join(" -> ")}"
        new Route(distance: step.distance, direction: current.direction)
    }

    List<Step> possibleStepsFrom(Step step, def exclude) {
        Position position = step.position
        def allSteps = [stepOf(position.offsetY(-1), UP),
                        stepOf(position.offsetX(-1), LEFT),
                        stepOf(position.offsetX(1), RIGHT),
                        stepOf(position.offsetY(1), DOWN)]

        allSteps
                .findAll { it.position in this && this[it.position].free && !(it.position in exclude) }
                .each {
                    it.previous = step
                    it.distance = step.distance + 1
                }
    }

    def movePlayer(Player player, Direction direction) {
        map[player.x][player.y].player = null
        player.move(direction)
        map[player.x][player.y].player = player
    }

    def attack(Player from, Player to) {
        to.attacked()
        if (to.isDead()) {
            log "Player $from killed $to"
            map[to.x][to.y].player = null
            players.remove(to)
        }
    }
}
