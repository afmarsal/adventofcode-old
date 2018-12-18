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
        forest.eachCellContent {
            if (it == "|") ++trees
            else if (it == "#") ++lumberyards
        }
        trees * lumberyards
    }

    static def test2(String input) {
        Forest forest = Forest.from(input)
        forest.printHeader()
        forest.print()

        def forestConfigurations = [:]
        forestConfigurations[forest] = 0

        def round = 0
        long repeatEvery
        def oldRound
        while(true) {
            ++round
            log "\n====Round ${round + 1}"
            forest = forest.transform()
            oldRound = forestConfigurations.putIfAbsent(forest, round)
            if (oldRound != null) {
                // Found repeated
                log "Found repetition at round $oldRound and $round"
                repeatEvery = round - oldRound
                break
            }
        }
        def finalRound = ((1_000_000_000 - oldRound) % repeatEvery) + oldRound
        def finalForest = forestConfigurations.find { it.value == finalRound }.key
        def trees = 0, lumberyards = 0
        finalForest.eachCellContent {
            if (it == "|") ++trees
            else if (it == "#") ++lumberyards
        }
        trees * lumberyards
    }
}
