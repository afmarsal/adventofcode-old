package jimpl.day23;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Collections.emptySet;

class Point3DAndRadius extends Point3D {

    private static final Pattern POINT3D_MATCHER
            = Pattern.compile("pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(-?\\d+)");

    public static final Point3DAndRadius FARTHEST = new Point3DAndRadius(MAX_VALUE, MAX_VALUE, MAX_VALUE, 0);

    private final int radius;

    private Point3DAndRadius(final int x,
                             final int y,
                             final int z,
                             final int radius) {
        super(x, y, z);
        this.radius = radius;
    }


    static Point3DAndRadius from(String line) {
        Matcher matcher = POINT3D_MATCHER.matcher(line);
        if (!matcher.matches()) throw new RuntimeException("Invalid point " + line);
        return new Point3DAndRadius(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4)));
    }

    boolean inRange(Point3D point3D) {
        return distance(point3D) <= radius;
    }

    int getMinX() {
        return x - radius;
    }

    int getMinY() {
        return y - radius;
    }

    int getMinZ() {
        return z - radius;
    }

    int getMaxX() {
        return x + radius;
    }

    int getMaxY() {
        return y + radius;
    }

    int getMaxZ() {
        return z + radius;
    }

    public int getRadius() {
        return radius;
    }

    boolean crossesX(final int x) {
        return (x >= getMinX()) && (x <= getMaxX());
    }

    boolean crossesY(int y) {
        return (y >= getMinY()) && (y <= getMaxY());
    }

    boolean crossesZ(int z) {
        return (z >= getMinZ()) && (z <= getMaxZ());
    }

    public Point3D getClosestTo0() {
        return Point3D.from(getMinX(), getMinY(), getMinZ());
    }

    @Override
    public String toString() {
        return String.format("{%d,%d,%d:%d}", x, y, z, radius);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Point3DAndRadius that = (Point3DAndRadius) o;
        return radius == that.radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }

    public boolean intersect(final Point3DAndRadius point3DAndRadius) {
        return distance(point3DAndRadius) <= radius + point3DAndRadius.radius;
    }
}
