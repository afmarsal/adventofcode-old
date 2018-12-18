package Day18

import static util.Logger.log

class Day18 {

    static def test1(String input, int rounds) {
        Forest forest = Forest.from(input)
        forest.printHeader()
        forest.print()

        rounds.times { round ->
            log "\n====Round ${round + 1}"
            forest = forest.transform()
        }

        def trees = 0, lumberyards = 0
        forest.eacCellContent {
            if (it == "|") ++trees
            else if (it == "#") ++lumberyards
        }
        trees * lumberyards
    }

}
