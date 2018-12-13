import spock.lang.Specification

class Day6Test extends Specification {

    def "test 1"() {

        expect:
            Day6.test1(input) == output

        where:
            input        | output
            SAMPLE_INPUT | 17
            FINAL_INPUT  | 3604
    }

    def "test 2"() {

        expect:
            Day6.test2(input, maxDistance) == output

        where:
            input        | maxDistance | output
            SAMPLE_INPUT | 32          | 16
            FINAL_INPUT  | 10000       | 46563
    }

    static final SAMPLE_INPUT = '''\
        1, 1
        1, 6
        8, 3
        3, 4
        5, 5
        8, 9'''.stripIndent()

    static final FINAL_INPUT = '''\
        252, 125
        128, 333
        89, 324
        141, 171
        266, 338
        117, 175
        160, 236
        234, 202
        165, 192
        204, 232
        83, 192
        229, 178
        333, 57
        70, 243
        108, 350
        161, 63
        213, 277
        87, 299
        163, 68
        135, 312
        290, 87
        73, 246
        283, 146
        80, 357
        66, 312
        159, 214
        221, 158
        175, 54
        298, 342
        348, 162
        249, 90
        189, 322
        311, 181
        194, 244
        53, 295
        80, 301
        262, 332
        268, 180
        139, 287
        115, 53
        163, 146
        220, 268
        79, 85
        95, 112
        349, 296
        179, 274
        113, 132
        158, 264
        316, 175
        268, 215'''.stripIndent()
}
