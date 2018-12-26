package day22java

import util.Position

import static CaveCellType.*

class CaveCell {

    static final CaveCellType[] TYPES = [ROCKY, WET, NARROW]
    static final Map RISK_LEVELS
    static {
        RISK_LEVELS = new EnumMap(CaveCellType.class)
        RISK_LEVELS[ROCKY] = 0
        RISK_LEVELS[WET] = 1
        RISK_LEVELS[NARROW] = 2
    }

    int geologicalIndex
    int erosionLevel
    CaveCellType type
    Position position

    def getRiskLevel() {
        RISK_LEVELS[type]
    }

    static CaveCell from(int row, int col, int geologicalIndex, int depth) {
        def erosionLevel = (geologicalIndex + depth) % 20183
        new CaveCell(
                geologicalIndex: geologicalIndex,
                erosionLevel: erosionLevel,
                type: TYPES[erosionLevel % 3],
                position: Position.posOf(row, col))
    }

    String getPic() {
        type.str
    }

    boolean equals(final o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        CaveCell caveCell = (CaveCell) o

        if (position != caveCell.position) return false

        return true
    }

    int hashCode() {
        return (position != null ? position.hashCode() : 0)
    }

    @Override
    public String toString() {
        "[$position]='$type'"
    }
}

