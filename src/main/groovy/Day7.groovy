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

    static String test2(String input, int availableWorkers, int offset) {
        OFFSET = offset
        def candidateSteps = readStepsAndGetRoots(input)

        List<WorkingStep> stepsInProgress = []
        Set<Step> finishedSteps = []
        StringBuilder result = new StringBuilder("")

        def second = 0
        while (candidateSteps || stepsInProgress) {
            println "$second: Idle: $availableWorkers, result: $result, Working on: ${stepsInProgress*.step.name.join()}, candidates: ${candidateSteps*.name.join(",")}"
            // Check which can star
            while (candidateSteps && availableWorkers > 0) {
                def candidate = findNextCandidate(candidateSteps)
                def workingStep = new WorkingStep(step: candidate, startedAt: second)
                stepsInProgress << workingStep
                --availableWorkers
                candidateSteps.remove candidate
                println "$second: Adding new working item: $workingStep"
            }
            // Check finished
            List<WorkingStep> haveFinished = stepsInProgress.findAll { it.hasFinishedAt(second) }
            if (haveFinished) {
                println "${second}: Finished workers: $haveFinished"
                finishedSteps.addAll haveFinished*.step
                candidateSteps.addAll haveFinished*.step.children.flatten().findAll { it.parents.every { finishedSteps.contains(it) } }
                stepsInProgress.removeAll haveFinished
                availableWorkers += haveFinished.size()
                result << haveFinished.collect { it.step.name }.sort().join()
            }
            ++second
        }
        println "$second: Idle: $availableWorkers, result: $result, Working on $stepsInProgress "
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
            currentTime == startedAt + OFFSET + (step.name.toCharacter() - "A".toCharacter())
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("WorkingStep{");
            sb.append("step=").append(step.name)
            sb.append(",parents: ${step.parents*.name.join(",")}")
            sb.append(",children: ${step.children*.name.join(",")}")
            sb.append(", startedAt=").append(startedAt);
            sb.append('}');
            return sb.toString();
        }
    }
}
