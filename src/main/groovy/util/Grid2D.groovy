package util

import static util.Logger.log
import static util.Position.posOf

class Grid2D {

    protected List<List> cells = []

    def getAt(Position position) {
        position.with {
            cells[row][col]
        }
    }

    def getAt(List coord) {
        assert coord.size() == 2
        cells[coord[0]][coord[1]]
    }

    def putAt(Position position, def value) {
        putAt(position.row, position.col, value)
    }

    def putAt(List coords, def value) {
        putAt(coords[0], coords[1], value)
    }

    def putAt(int row, int col, def value) {
        if (row < 0) {
            throw new RuntimeException("Can't put on negative X")
        }
        if (row >= cells.size()) {
//            log("Growing rows to ${row}")
            cells.size().upto(row) {
                cells[it] = []
            }
        }
        if (col < 0) {
            throw new RuntimeException("Can't put on negative Y")
        }
//        if (col >= cells[0].size()) {
//            log("Growing row,cols to ${row},${col}")
//        }
//        log "Setting $row,$col = $value"
        cells[row][col] = value
    }

    boolean isCase(Position position) {
        0 <= position.row && position.row < cells.size() && 0 <= position.col && position.col < cells[0].size()
    }

    boolean isCase(List coord) {
        def (row, col) = coord
        0 <= row && row < cells.size() && 0 <= col && col < cells[0].size()
    }

    def getWidth() {
        cells[0].size()
    }

    def getHeight() {
        cells.size()
    }

    def printHeader() {
        log "Grid with size [${width}x${height}]"
    }

    def print(Position pos = null) {
        def sb = new StringBuilder("  ")
        0.upto(width - 1) { sb.append("${it % 10} ") }
        sb.append("\n")
        for (int row = 0; row < height; row++) {
            sb.append("${row % 10} ")
            for (int col = 0; col < width; col++) {
                if (posOf(col, row) == pos) {
                    sb.append("X ")
                } else {
                    sb.append("${this[row, col]} ")
                }
            }
            sb.append("\n")
        }
        log sb.toString()
    }

    def eachCellContent(Closure closure) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                closure.call(this[row, col])
            }
        }
    }

    String toSingleLine() {
        StringBuilder builder = new StringBuilder()
        this.eachCellContent { builder.append(it) }
        builder.toString()
    }

    boolean equals(final o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Grid2D grid2D = (Grid2D) o

        this.toSingleLine() == grid2D.toSingleLine()
    }

    int hashCode() {
        return (toSingleLine() != null ? toSingleLine().hashCode() : 0)
    }
}
