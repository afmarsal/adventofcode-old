class Day7 {

    static String test1(String input) {

        def candidates = readStepsAndGetRoots(input)

        Set<Step> finished = []
        StringBuilder result = new StringBuilder("")

        while (candidates) {
            def toBeRemoved = findNextCandidate(candidates)
            candidates.remove toBeRemoved
            finished << toBeRemoved
            if (toBeRemoved.children) {
                candidates.addAll toBeRemoved.children.findAll { it.parents.every { finished.contains(it) } }
            }
            result << toBeRemoved.name
        }
        result
    }

    static int OFFSET

    static String test2(String input, int workers, int offset) {
        OFFSET = offset
        def candidates = readStepsAndGetRoots(input)

        List<WorkingStep> working = []
        Set<Step> finished = []
        StringBuilder result = new StringBuilder("")

        def second = 0
        while (candidates || working) {
            // Check finished
            println "$second: Idle: $workers, result: $result, Working on $working "
            List<WorkingStep> haveFinished = working.findAll { it.hasFinishedAt(second) }
            if (haveFinished) {
                println "${second-1}: Finished workers: $haveFinished"
                finished.addAll haveFinished*.step
                candidates.addAll haveFinished*.step.children.flatten().findAll { it.parents.every { finished.contains(it) } }
                working.removeAll haveFinished
                workers += haveFinished.size()
                result << haveFinished.collect { it.step.name }.sort().join()
            }
            // Check if any can start
            if (candidates && workers > 0) {
                def candidate = findNextCandidate(candidates)
                def workingStep = new WorkingStep(step: candidate, startedAt: second)
                working << workingStep
                --workers
                candidates.remove candidate
                println "$second: Adding new working item at $second -> $workingStep"
            } else {
                second++
            }
        }
        println "$second: Idle: $workers, result: $result, Working on $working "
        result
    }

    private static Step findNextCandidate(List<Step> candidates) {
        candidates.min { a, b -> a.name.compareTo(b.name) }
    }

    private static List<Step> readStepsAndGetRoots(String input) {
        Map<String, Step> steps = [:]
        input.readLines().each {
            def m = it =~ /Step (\w) must be finished before step (\w) can begin./
            if (!m) throw new RuntimeException("Invalid line $it")
            def parent = steps.computeIfAbsent m.group(1), { Step.from(it) }
            def child = steps.computeIfAbsent m.group(2), { Step.from(it) }
            parent.children << child
            child.parents << parent
        }
        // Get root steps (steps without parents)
        steps.values().findAll { !it.parents }

    }

    static class Step {

        String name
        List<Step> parents = []
        List<Step> children = []

        static Step from(String stepName) {
            new Step(name: stepName)
        }

        boolean equals(final o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            Step step = (Step) o

            if (name != step.name) return false

            return true
        }

        int hashCode() {
            return name.hashCode()
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Step{");
            sb.append("name='").append(name).append('\'');
            sb.append(", parents=").append(parents*.name.join(","));
            sb.append(", children=").append(children*.name.join(","));
            sb.append("}\n");
            return sb.toString();
        }
    }

    static class WorkingStep {
        int startedAt
        Step step

        boolean hasFinishedAt(def currentTime) {
            currentTime > startedAt + OFFSET + (step.name.toCharacter() - "A".toCharacter())
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("WorkingStep{");
            sb.append("step=").append(step.name);
            sb.append(", startedAt=").append(startedAt);
            sb.append('}');
            return sb.toString();
        }
    }
}
