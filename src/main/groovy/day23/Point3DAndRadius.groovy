package day23

import java.util.regex.Pattern

class Point3DAndRadius extends Point3D {

    static final Pattern POINT3D_MATCHER = Pattern.compile(/pos=<(-?\d+),(-?\d+),(-?\d+)>, r=(-?\d+)/)
    int radius

    static Point3DAndRadius from(String line) {
        def matcher = line =~ POINT3D_MATCHER
        if (!matcher) throw new RuntimeException("Invalid point $line")
        new Point3DAndRadius(
                x: matcher[0][1].toInteger(),
                y: matcher[0][2].toInteger(),
                z: matcher[0][3].toInteger(),
                radius: matcher[0][4].toInteger())
    }

    boolean inRange(Point3DAndRadius point3D) {
        distance(point3D) <= radius
    }

    int getMinX() {
        x - radius
    }

    int getMinY() {
        y - radius
    }

    int getMinZ() {
        z - radius
    }

    int getMaxX() {
        x + radius
    }

    int getMaxY() {
        y + radius
    }

    int getMaxZ() {
        z + radius
    }

    boolean crossesX(int x) {
        x in minX..maxX
    }

    boolean crossesY(int y) {
        y in minY..maxY
    }

    boolean crossesZ(int z) {
        z in minZ..maxX
    }

    @Override
    String toString() {
        "{$x,$y,$z,r:$radius}"
    }

    boolean equals(final o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false
        if (!super.equals(o)) return false

        Point3DAndRadius that = (Point3DAndRadius) o

        if (radius != that.radius) return false

        return true
    }

    int hashCode() {
        int result = super.hashCode()
        result = 31 * result + radius
        return result
    }
}