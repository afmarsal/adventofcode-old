package day22

import util.Grid2D

import static day22.Day22.Tools.*

class Day22 {

    static final ROCKY = "."
    static final WET = "="
    static final NARROW = "|"

    static final Map TYPES = [0: ROCKY, 1: WET, 2: NARROW]
    static final Map RISK_LEVELS = [(ROCKY): 0, (WET): 1, (NARROW): 2]
    static final Map SUPPORTED_TOOLS = [(ROCKY): [CLIMBING_GEAR, TORCH],
                                        (WET): [CLIMBING_GEAR, NEITHER],
                                        (NARROW): [TORCH, NEITHER]]

    static def test1(int depth, def target) {

        def grid = new Grid2D()

        for (int row = 0; row <= target[0]; ++row) {
            for (int col = 0; col <= target[1]; ++col) {

                def geologicalIndex
                if (row == 0 && col == 0) {
                    geologicalIndex = 0

                } else if (row == target[0] && col == target[1]) {
                    geologicalIndex = 0

                } else if (col == 0) {
                    geologicalIndex = row * 16807

                } else if (row == 0) {
                    geologicalIndex = col * 48271

                } else {
                    geologicalIndex = grid[row, col - 1].erosionLevel * grid[row - 1, col].erosionLevel
                }
                def erosionLevel = (geologicalIndex + depth) % 20183
                grid[row, col] = new CaveCell(geologicalIndex: geologicalIndex, erosionLevel: erosionLevel, type: TYPES[erosionLevel % 3])
            }
        }
        grid.print()
        grid.collect { it.riskLevel }.sum()
    }

    static def test2(int depth, def target) {

        def grid = new Grid2D()

        for (int row = 0; row <= target[0]; ++row) {
            for (int col = 0; col <= target[1]; ++col) {

                def geologicalIndex
                if (row == 0 && col == 0) {
                    geologicalIndex = 0

                } else if (row == target[0] && col == target[1]) {
                    geologicalIndex = 0

                } else if (col == 0) {
                    geologicalIndex = row * 16807

                } else if (row == 0) {
                    geologicalIndex = col * 48271

                } else {
                    geologicalIndex = grid[row, col - 1].erosionLevel * grid[row - 1, col].erosionLevel
                }
                def erosionLevel = (geologicalIndex + depth) % 20183
                grid[row, col] = new CaveCell(geologicalIndex: geologicalIndex, erosionLevel: erosionLevel, type: TYPES[erosionLevel % 3])
            }
        }
        grid.print()
        grid.collect { it.riskLevel }.sum()
    }
    static class CaveCell {
        int geologicalIndex
        int erosionLevel
        String type
        int distance

        def getRiskLevel() {
            RISK_LEVELS[type]
        }

        @Override
        public String toString() {
            type
        }
    }

    enum Tools {
        NEITHER, TORCH, CLIMBING_GEAR
    }
}
