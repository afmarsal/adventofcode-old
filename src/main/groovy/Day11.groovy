class Day11 {

    static final GRID_SIZE = 300

    static def cellsFor(int serial) {
        int[][] cells = new int[GRID_SIZE][GRID_SIZE]

        GRID_SIZE.times { x ->
            GRID_SIZE.times { y ->
                def rackId = (x + 1) + 10
                def powerLevel = rackId * (y + 1)
                powerLevel += serial
                powerLevel *= rackId
                powerLevel = powerLevel.intdiv(100) % 10
                powerLevel -= 5
                cells[x][y] = powerLevel
            }
        }
        cells
    }

    static def maxCellPowerFor(int serial) {
        def cells = cellsFor(serial)

        def maxX, maxY, maxPower = Integer.MIN_VALUE
        (GRID_SIZE - 2).times { x ->
            (GRID_SIZE - 2).times { y ->
                def power = cells[x..x + 2]*.getAt(y..y + 2).flatten().sum()
                if (power > maxPower) {
                    (maxX, maxY, maxPower) = [x, y, power]
                }
            }
        }
        println "Max power for serial ${serial}: ${maxX}, $maxY, $maxPower"
        [maxX + 1, maxY + 1]
    }

    static def maxCellPowerWithSizeFor(int serial) {
        def cells = cellsFor(serial)
        int maxX, maxY, maxSize, maxPower = Integer.MIN_VALUE
        (GRID_SIZE - 1).times { x ->
            println "Processing x: $x"
            (GRID_SIZE - 1).times { y ->
                def accumPower = 0
                for (int size = 0; x + size < GRID_SIZE && y + size < GRID_SIZE; ++size) {
                    y.upto(y + size, { accumPower += cells[x + size][it] })
                    x.upto(x + size, { accumPower += cells[it][y + size] })
                    accumPower -= cells[x + size][y + size]
//                    println "cells[$x,$y] = ${cells[x][y]}, size = ${size + 1}, power = $accumPower"
                    if (accumPower > maxPower) {
//                        println "Max power updated"
                        (maxX, maxY, maxSize, maxPower) = [x, y, size + 1, accumPower]
                    }
                }
            }
        }
        println "Max power for serial ${serial}: ${maxX}, $maxY, $maxSize, $maxPower"
        [maxX + 1, maxY + 1, maxSize]
    }

}
