class Day9 {

    static long test1(int players, int lastMarble) {
        LinkedList<Long> marbles = [0, 1]
        def iterator = marbles.listIterator(1)
        long[] scores = new int[players]
        2.upto(lastMarble, { marble ->
            if (marble % 23 == 0) {
                7.times {
                    if (iterator.hasPrevious()) iterator.previous()
                    else iterator = marbles.listIterator(marbles.size()-1)
                }
                def scoringPlayer = (marble % players)
                scores[scoringPlayer] += (marble + iterator.next())
                iterator.previous()
                iterator.remove()

            } else {
                2.times {
                    if (iterator.hasNext()) iterator.next()
                    else iterator = marbles.listIterator(1)
                }
                iterator.add marble
                iterator.previous()
            }

//            if (marble % 100 == 0) {
//                marbles.eachWithIndex { it, i -> if (i == iterator.nextIndex()) print("*$it,") else print("$it,") }
//                println ""
//            }
        })
        scores.toList().max()
    }

}
