package day23

class CoordEvent {
    CoordEventType eventType
    Point3DAndRadius point

    static CoordEvent eventOf(CoordEventType eventType, Point3DAndRadius point) {
        new CoordEvent(eventType: eventType, point: point)
    }

    boolean equals(final o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        CoordEvent xEvent = (CoordEvent) o

        if (eventType != xEvent.eventType) return false
        if (point != xEvent.point) return false

        return true
    }

    int hashCode() {
        int result
        result = (eventType != null ? eventType.hashCode() : 0)
        result = 31 * result + (point != null ? point.hashCode() : 0)
        return result
    }

    @Override
    public String toString() {
        "{$eventType,$point}"
    }

}