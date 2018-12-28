package day23

import java.util.regex.Pattern

class Point3DAndRadius extends Point3D implements Comparable<Point3DAndRadius> {

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

    @Override
    int compareTo(final Point3DAndRadius o) {
        def result = Integer.compare(minZ, o.minZ)
        if (result != 0) return result
        result = Integer.compare(minY, o.minY)
        if (result != 0) return result
        Integer.compare(minX, o.minX)
    }

    boolean equals(final o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false


        Point3DAndRadius point4D = (Point3DAndRadius) o

        if (x != point4D.x) return false
        if (y != point4D.y) return false
        if (z != point4D.z) return false

        return true
    }

    int hashCode() {
        int result
        result = x
        result = 31 * result + y
        result = 31 * result + z
        return result
    }
}