package day22java

import util.Position

class CaveCellAndTool implements Comparable<CaveCellAndTool> {

    Id id
    CaveCellAndTool comingFrom
    int distance
    int _hScore

    static CaveCellAndTool from(CaveCell cell, Tools tool, int distance, Position target) {
        Id id = new Id(cell: cell, tool: tool)
        new CaveCellAndTool(id: id, distance: distance, _hScore: cell.position.distance(target), comingFrom: null)
    }

    Position getPosition() {
        return id.cell.position
    }

    Tools getTool() {
        id.tool
    }

    CaveCellType getType() {
        return id.cell.type
    }

    int getHScore() {
        distance + _hScore
    }

    boolean equals(final o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        CaveCellAndTool that = (CaveCellAndTool) o

        if (id != that.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }

    @Override
    public String toString() {
        "[${position.row},${position.col}]=[${tool},${distance}+${_hScore}]"
    }

    @Override
    int compareTo(final CaveCellAndTool o) {
        def result = getHScore() <=> o.getHScore()
        if (result != 0) return result
        result = position <=> o.position
        if (result != 0) return result
        tool.ordinal() - o.tool.ordinal()
    }

    static class Id {
        CaveCell cell
        Tools tool

        boolean equals(final o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            Id id = (Id) o

            if (cell != id.cell) return false
            if (tool != id.tool) return false

            return true
        }

        int hashCode() {
            int result
            result = (cell != null ? cell.hashCode() : 0)
            result = 31 * result + (tool != null ? tool.hashCode() : 0)
            return result
        }
    }
}
