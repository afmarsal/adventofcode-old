class Day6 {

    static def test1(String input) {
        def cells = input.readLines().collect { Cell.from(it) }.toSet()

        def minX = cells.min { it.x }.x
        def minY = cells.min { it.y }.y
        def maxX = cells.max { it.x }.x
        def maxY = cells.max { it.y }.y
//        println "Size: [$minX,$minY] x [$maxX, $maxY]"
        // Skew points
        cells.each {
            it.x -= minX
            it.y -= minY
        }
        minX = cells.min { it.x }.x
        minY = cells.min { it.y }.y
        maxX = cells.max { it.x }.x
        maxY = cells.max { it.y }.y
//        println "Size: [$minX,$minY] x [$maxX, $maxY]"
        char[][] grid = new int[maxX + 1][maxY + 1]
        def gridCellsByCoord = [:]
        def cellsInBorder = [] as Set

        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                def distances = cells.collectEntries { [it, it.distance(i, j)] }
                def minDistance = distances.values().min()
                def closestCells = distances.findAll { k, v -> v == minDistance }.keySet()
                if (closestCells.size() > 1) {
//                    println "Tie for cell $i,$j: distance $minDistance for $closestCells"
                    grid[i][j] = "."
                } else {
                    Cell closestCell = closestCells[0]
                    if (minDistance == 0) {
                        grid[i][j] = closestCell.id
                    } else {
                        grid[i][j] = closestCell.id.toLowerCase()
                    }
                    gridCellsByCoord.merge(closestCell, 1, { v1, v2 -> v1 + v2 })
                    if (i == 0 || i == maxX || j == 0 || j == maxY) {
                        cellsInBorder << closestCell
                    }
                }
            }
        }
//        for (int i = 0; i <= maxY; i++) {
//            for (int j = 0; j <= maxX; j++) {
//                print(grid[j][i])
//            }
//            println ""
//        }
        gridCellsByCoord.removeAll { k, v -> k in cellsInBorder }
        gridCellsByCoord.max { it.value }.value
    }

    static class Cell {
        static idx = 0
        String id
        int x, y

        static Cell from(String s) {
            def m = s =~ /(\d+), (\d+)/
            if (!m) throw new RuntimeException("Invalid line $s")
            new Cell(x: m[0][1].toInteger(), y: m[0][2].toInteger(), id: nextId())
        }

        static Cell of(int x, int y) {
            new Cell(x: x, y: y)
        }

        static String nextId() {
            Character.toString((char) ((('A' as char) + idx++)))
        }

        int distance(int x, int y) {
            Math.abs(x - this.x) + Math.abs(y - this.y)
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            Cell cell = (Cell) o

            if (x != cell.x) return false
            if (y != cell.y) return false

            return true
        }

        int hashCode() {
            int result
            result = x
            result = 31 * result + y
            return result
        }


        @Override
        public String toString() {
            return "Cell{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
