class Day7 {

    static String test1(String input) {

        Map<String, Step> steps = [:]
        input.readLines().each {
            def m = it =~ /Step (\w) must be finished before step (\w) can begin./
            if (!m) throw new RuntimeException("Invalid line $it")
            def parent = steps.computeIfAbsent m.group(1), { Step.from(it) }
            def child = steps.computeIfAbsent m.group(2), { Step.from(it) }
            parent.children << child
            child.parents << parent
        }

        def roots = steps.findAll { !it.value.parents }
        Set<Step> candidates = roots*.value
        Set<Step> used = []

        StringBuilder result = new StringBuilder("")
        while (candidates) {
            def min = candidates.min { a, b -> a.name.compareTo(b.name) }
            candidates.remove min
            used << min
            if (min.children) {
                candidates.addAll min.children.findAll { it.parents.every { used.contains(it) } }
            }
            result << min.name
        }
        result
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
}
