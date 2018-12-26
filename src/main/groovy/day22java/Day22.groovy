package day22java

import util.Position

import static CaveCellType.ROCKY
import static Tools.CLIMBING_GEAR
import static Tools.TORCH
import static util.Logger.log
import static util.Position.posOf

class Day22 {

    static def test1(int depth, def target) {
        def grid = Cave.from(depth, target[0], target[1])
        grid.print()
        grid.collect { it.riskLevel }.sum()
    }


    static def test2(int depth, def targetCoord) {

        Position target = posOf(targetCoord)
        def maxX = target.row * 100
        def maxY = target.col * 10
        def cave = Cave.from(depth, maxX, maxY)
        // Target is always rocky
        if (cave[target].type != ROCKY) {
            println "Switching target type from ${cave[target].type} to ROCKY"
            cave[target].type = ROCKY
        }
//        cave.printTransposed()
//        println "Cave generated"

        def firstCellTorch = CaveCellAndTool.from(cave[0, 0], TORCH, 0, target)
        def firstCellClimbing = CaveCellAndTool.from(cave[0, 0], CLIMBING_GEAR, 7, target)
        // Hacky, but I need to make a set of cells and be able to retrieve them from the set
//        SortedMap<CaveCellAndTool.Id, CaveCellAndTool> pending = [(firstCellTorch.id): firstCellTorch, (firstCellClimbing.id): firstCellClimbing] as TreeMap
        PriorityQueue<CaveCellAndTool> pendingQueue = new PriorityQueue<>(2_000_000)
        Map<CaveCellAndTool.Id, CaveCellAndTool> allPending = new HashMap()
        pendingQueue.offer(firstCellTorch)
        pendingQueue.offer(firstCellClimbing)
        allPending[firstCellTorch.id] = firstCellTorch
        allPending[firstCellClimbing.id] = firstCellClimbing
        Set<CaveCellAndTool> visited = new HashSet<>(2_000_000)

        int counter = 0
        while (!pendingQueue.empty) {

            CaveCellAndTool cell = pendingQueue.poll()
            allPending.remove(cell.id)
            visited << cell
            if (cell.position == target) {
                log("Found!")
                return cell.distance
            }
            if (counter % 100_000 == 0) {
                println "$counter> Pending ${pendingQueue.size()}"
                println "$counter> Visited ${visited.size()}"
                println "$counter> Processing $cell"
            }
            ++counter
            log "*********"
//            log "Pending ${pending.collect { "[${it.key}]" }.join(",")}"
//            log "Visited ${visited.collect { "[${it}]" }.join(",")}"
            log "$counter> Pending ${pendingQueue.size()}"
            log "$counter> Visited ${visited.size()}"
            log "$counter> Processing $cell"
            def adjacents = cave.get4Adjacent(cell.position)
//            if (adjacents.size() < 4) {
//                println "** Some edge reached"
//                log "Pending ${pending.collect { "[${it}]" }.join(",")}"
//                log "Visited ${visited.collect { "[${it}]" }.join(",")}"
//                log "Processing $cell"
//                log "Adjacents (before removing): ${adjacents.collect { "[${it}]" }.join(",")}"
//            }

            adjacents.each { CaveCell adjacent ->

                cell.type.supportedTools.each { tool ->

                    if (!adjacent.type.supports(tool)) {
                        return
                    }
                    def newDistance = cell.distance + 1
                    if (tool != cell.tool) {
                        newDistance += 7
                    }
                    if (adjacent.position == target) {
                        if (tool == CLIMBING_GEAR) {
                            newDistance += 7
                        }
                    }
                    CaveCellAndTool adjacentWithTool = CaveCellAndTool.from(adjacent, tool, newDistance, target)
                    def previousAdjacent = allPending.putIfAbsent(adjacentWithTool.id, adjacentWithTool)
                    if (previousAdjacent == null) {
                        pendingQueue.offer(adjacentWithTool)
                    }
                    if (previousAdjacent != null && previousAdjacent.distance > adjacentWithTool.distance) {
                        pendingQueue.remove(previousAdjacent)
                        pendingQueue.offer(adjacentWithTool)
                    }
                }
            }

        }
        if (pendingQueue.empty) {
            log "No pending nodes!!!"
        } else {
            println "Reached destination: ${cave[target]}"
        }
//        printRoute(cave[target])
//        cave[target].distance
    }

    def static printRoute(CaveCell cell) {
        log "Shortest route: "
        while (cell != null) {
            log cell
            cell = cell.comingFrom
        }
    }

}
