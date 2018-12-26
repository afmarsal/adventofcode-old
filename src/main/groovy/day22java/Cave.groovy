package day22java

import util.Grid2D

class Cave extends Grid2D {

    private static def from(int depth, def maxX, def maxY) {
        Cave cave = new Cave()
        for (int row = 0; row <= maxX; ++row) {
            for (int col = 0; col <= maxY; ++col) {

                def geologicalIndex
                if (row == 0 && col == 0) {
                    geologicalIndex = 0

                } else if (row == maxX && col == maxY) {
                    geologicalIndex = 0

                } else if (col == 0) {
                    geologicalIndex = row * 16807

                } else if (row == 0) {
                    geologicalIndex = col * 48271

                } else {
                    geologicalIndex = cave[row, col - 1].erosionLevel * cave[row - 1, col].erosionLevel
                }
                cave[row, col] = CaveCell.from(row, col, geologicalIndex, depth)
            }
        }
        cave
    }

}

