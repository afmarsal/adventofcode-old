package jimpl.day23;

import java.util.Objects;

class CoordEvent {

    private final CoordEventType eventType;
    private final Point3DAndRadius point;

    CoordEvent(final CoordEventType eventType, final Point3DAndRadius point) {
        this.eventType = eventType;
        this.point = point;
    }

    public static CoordEvent eventOf(CoordEventType eventType, Point3DAndRadius point) {
        return new CoordEvent(eventType, point);
    }

    public CoordEventType getEventType() {
        return eventType;
    }

    public Point3DAndRadius getPoint() {
        return point;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordEvent that = (CoordEvent) o;
        return eventType == that.eventType &&
                Objects.equals(point, that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, point);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CoordEvent{");
        sb.append("eventType=").append(eventType);
        sb.append(", point=").append(point);
        sb.append('}');
        return sb.toString();
    }
}
