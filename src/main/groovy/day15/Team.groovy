package day15

enum Team {
    GOBLIN, ELF

    static Optional<Team> from(String c) {
        if (c == "G") return Optional.of(GOBLIN)
        else if (c == "E") return Optional.of(ELF)
        else return Optional.empty()
    }

    @Override
    String toString() {
        this == GOBLIN ? "G" : "E"
    }
}
