package day22java

import static Tools.*

enum CaveCellType {
    ROCKY("."), WET("="), NARROW("|")

    static final Map SUPPORTED_TOOLS = [(ROCKY) : EnumSet.of(CLIMBING_GEAR, TORCH),
                                        (WET)   : EnumSet.of(CLIMBING_GEAR, NEITHER),
                                        (NARROW): EnumSet.of(TORCH, NEITHER)]

    def str

    CaveCellType(final def str) {
        this.str = str
    }

    boolean supports(Tools tool) {
        tool in SUPPORTED_TOOLS[this]
    }

    Set<Tools> getSupportedTools() {
        SUPPORTED_TOOLS[this]
    }

    @Override
    public String toString() {
        str
    }
}