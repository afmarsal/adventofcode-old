import spock.lang.Specification

class Day7Test extends Specification {

    def "test 1"() {

        expect:
            Day7.test1(input) == output

        where:
            input        | output
            SAMPLE_INPUT | "CABDFE"
            FINAL_INPUT  | "BKCJMSDVGHQRXFYZOAULPIEWTN"
    }

    def "test 2"() {

        expect:
            Day7.test2(input, workers, offset) == output

        where:
            input        | workers | offset | output
            SAMPLE_INPUT | 2       | 0      | 15
            FINAL_INPUT  | 5       | 60     | 1040
    }

    private static final String SAMPLE_INPUT = '''\
        Step C must be finished before step A can begin.
        Step C must be finished before step F can begin.
        Step A must be finished before step B can begin.
        Step A must be finished before step D can begin.
        Step B must be finished before step E can begin.
        Step D must be finished before step E can begin.
        Step F must be finished before step E can begin.'''.stripIndent()

    private static final String FINAL_INPUT = '''\
Step B must be finished before step X can begin.
Step V must be finished before step F can begin.
Step K must be finished before step C can begin.
Step S must be finished before step D can begin.
Step C must be finished before step A can begin.
Step H must be finished before step X can begin.
Step Q must be finished before step W can begin.
Step X must be finished before step F can begin.
Step J must be finished before step R can begin.
Step D must be finished before step O can begin.
Step F must be finished before step P can begin.
Step M must be finished before step Z can begin.
Step R must be finished before step I can begin.
Step Y must be finished before step O can begin.
Step G must be finished before step Z can begin.
Step Z must be finished before step P can begin.
Step O must be finished before step L can begin.
Step A must be finished before step P can begin.
Step U must be finished before step L can begin.
Step L must be finished before step W can begin.
Step P must be finished before step W can begin.
Step I must be finished before step W can begin.
Step E must be finished before step N can begin.
Step W must be finished before step N can begin.
Step T must be finished before step N can begin.
Step G must be finished before step E can begin.
Step K must be finished before step T can begin.
Step I must be finished before step T can begin.
Step V must be finished before step H can begin.
Step W must be finished before step T can begin.
Step M must be finished before step A can begin.
Step C must be finished before step W can begin.
Step B must be finished before step Y can begin.
Step Y must be finished before step N can begin.
Step L must be finished before step N can begin.
Step M must be finished before step R can begin.
Step L must be finished before step I can begin.
Step J must be finished before step N can begin.
Step K must be finished before step M can begin.
Step O must be finished before step U can begin.
Step P must be finished before step N can begin.
Step Y must be finished before step I can begin.
Step V must be finished before step Q can begin.
Step H must be finished before step R can begin.
Step M must be finished before step P can begin.
Step K must be finished before step L can begin.
Step J must be finished before step A can begin.
Step D must be finished before step F can begin.
Step Q must be finished before step P can begin.
Step C must be finished before step H can begin.
Step U must be finished before step I can begin.
Step A must be finished before step T can begin.
Step C must be finished before step P can begin.
Step U must be finished before step T can begin.
Step O must be finished before step T can begin.
Step O must be finished before step I can begin.
Step S must be finished before step I can begin.
Step Z must be finished before step E can begin.
Step Y must be finished before step T can begin.
Step K must be finished before step O can begin.
Step O must be finished before step A can begin.
Step Z must be finished before step T can begin.
Step Z must be finished before step U can begin.
Step U must be finished before step P can begin.
Step P must be finished before step I can begin.
Step S must be finished before step W can begin.
Step S must be finished before step P can begin.
Step S must be finished before step Q can begin.
Step C must be finished before step E can begin.
Step G must be finished before step U can begin.
Step D must be finished before step L can begin.
Step K must be finished before step S can begin.
Step R must be finished before step O can begin.
Step C must be finished before step G can begin.
Step V must be finished before step G can begin.
Step A must be finished before step W can begin.
Step Z must be finished before step O can begin.
Step J must be finished before step O can begin.
Step F must be finished before step E can begin.
Step U must be finished before step E can begin.
Step E must be finished before step W can begin.
Step M must be finished before step O can begin.
Step C must be finished before step U can begin.
Step G must be finished before step P can begin.
Step C must be finished before step I can begin.
Step Z must be finished before step A can begin.
Step C must be finished before step J can begin.
Step Q must be finished before step R can begin.
Step E must be finished before step T can begin.
Step F must be finished before step Y can begin.
Step Z must be finished before step N can begin.
Step I must be finished before step N can begin.
Step X must be finished before step E can begin.
Step I must be finished before step E can begin.
Step Q must be finished before step O can begin.
Step R must be finished before step L can begin.
Step K must be finished before step W can begin.
Step Y must be finished before step L can begin.
Step M must be finished before step I can begin.
Step F must be finished before step O can begin.
Step A must be finished before step E can begin.'''.stripIndent()
}
