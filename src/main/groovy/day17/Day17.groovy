package day17


import static util.Logger.log

class Day17 {

    static def test1(String input) {
        new Day17().task1(input)
    }

    def task1(String input) {
        def cave = parseInput(input)
    }

    def parseInput(String input) {
        def matrix = [][]
        input.readLines().each { line ->
            def matcherX = line =~ /x=(\d+), y=(\d+\.\.\d+)$/
            def matcherY = line =~ /y=(\d+), x=(\d+\.\.\d+)$/

            log "Processing line $line"
            if (matcherX) {
                int x = matcherX[0][1].toInteger()
                IntRange y = Eval.me(matcherX[0][2])
                if (x >= matrix.size()) {
                    (matrix.size()..x).each {
                        matrix[it] = []
                    }
                }
                y.each {
                    matrix[x][it] = "#"
                    log "Putting clay on $x, $it"
                }

            } else if (matcherY) {
                int y = matcherY[0][1].toInteger()
                IntRange x = Eval.me(matcherY[0][2])
                x.each {
                    if (it >= matrix.size()) {
                        matrix[it] = []
                    }
                    matrix[it][y] = "#"
                    log "Putting clay on $it, $y"
                }
            } else {
                throw new RuntimeException("Invalid line $line")
            }
        }
        // Normalize
        def minX = findFirstPosNotMatching(matrix, []) - 1
        def maxX = matrix.size()
        def width = maxX - minX + 1 // Add a column by each side
        def minY = matrix.collect { findFirstPosNotMatching(it, null) }.min()
        def maxY = matrix.max { it.size() }.size() - 1
        def height = maxY - minY + 1
        println "Normalizing matrix to: $minX, $minY - $maxX, $maxY. size: ${width}x${height}"
        def cave = []
        width.times { x ->
            cave[x] = []
            height.times { y ->
                def mx = minX + x
                def my = minY + y
                cave[x][y] = ((matrix[minX + x][minY + y] == null) ? "." : "#")
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                print cave[x][y]
            }
            println ""
        }
    }

    int findFirstPosNotMatching(def list, def what) {
        for (int i = 0; i < list.size(); ++i) {
            if (list[i] != what) return i
        }
        return Integer.MAX_VALUE
    }
}
