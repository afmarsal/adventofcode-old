package Day18

import util.Grid2D

import static util.Logger.log

class Forest extends Grid2D {

    static Forest from(final String input) {
        Forest result = new Forest()
        input.readLines().eachWithIndex { String line, int row ->
            line.eachWithIndex { String ch, int col ->
                result[row, col] = ch
            }
        }
        result
    }

    Forest transform() {
        def forest = this
        Forest newForest = new Forest()
        for (def row = 0; row < forest.width; ++row) {
            for (int col = 0; col < forest.height; ++col) {
                def adjacentContents = forest.adjacentContents(row, col)
                switch (forest[row, col]) {
                case ".":
                    if (count(adjacentContents, "|") >= 3) {
                        newForest[row, col] = "|"
                    } else {
                        newForest[row, col] = "."
                    }
                    break
                case "|":
                    if (count(adjacentContents, "#") >= 3) {
                        newForest[row, col] = "#"
                    } else {
                        newForest[row, col] = "|"
                    }
                    break
                case "#":
                    if (count(adjacentContents, "#") >= 1 && count(adjacentContents, "|") >= 1) {
                        newForest[row, col] = "#"
                    } else {
                        newForest[row, col] = "."
                    }
                    break
                }
            }
        }
        newForest.print()
        newForest
    }

    private def count(def adjacent, def content) {
        adjacent.sum { it == content ? 1 : 0 }
    }

    List<String> adjacentContents(int row, int col) {
        def result = []
        def adjacentPos = [
                [row - 1, col - 1], [row - 1, col], [row - 1, col + 1],
                [row, col - 1], [row, col + 1],
                [row + 1, col - 1], [row + 1, col], [row + 1, col + 1],
        ]
        adjacentPos.each {
            if (it in this) {
                result << this[it]
            }
        }
        result
    }

}