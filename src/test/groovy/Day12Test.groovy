import spock.lang.Specification

class Day12Test extends Specification {

    def "test 1"() {
        expect:
            Day12.test1(input, 20) == output

        where:
            input        | output
            SAMPLE_INPUT | 325
            FINAL_INPUT  | 3337
    }

    def "test 2"() {
        expect:
            Day12.test1(input, 50_000_000_000) == output

        where:
            input        | output
            FINAL_INPUT  | 4300000000349
    }

    static final SAMPLE_INPUT = '''\
        initial state: #..#.#..##......###...###
        
        ...## => #
        ..#.. => #
        .#... => #
        .#.#. => #
        .#.## => #
        .##.. => #
        .#### => #
        #.#.# => #
        #.### => #
        ##.#. => #
        ##.## => #
        ###.. => #
        ###.# => #
        ####. => #'''.stripIndent()

    static final String FINAL_INPUT = '''\
        initial state: #...#####.#..##...##...#.##.#.##.###..##.##.#.#..#...###..####.#.....#..##..#.##......#####..####...
        
        #.#.# => #
        ..### => .
        #..#. => #
        .#... => #
        ..##. => #
        ##.#. => #
        ##..# => #
        ####. => #
        ...#. => #
        ..#.# => #
        .#### => #
        #.### => .
        ...## => .
        ..#.. => .
        #...# => .
        .###. => #
        .#.## => .
        .##.. => #
        ....# => .
        #..## => .
        ##.## => #
        #.##. => .
        #.... => .
        ##... => #
        .#.#. => .
        ###.# => #
        ##### => #
        #.#.. => .
        ..... => .
        .##.# => .
        ###.. => .
        .#..# => .'''.stripIndent()
}
