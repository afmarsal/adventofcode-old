package day23

import java.util.regex.Pattern

class Day23 {

    static def test1(String input) {

        def points = input.readLines().collect { Point3D.from(it) }
        def maxRadius = points.max { it.radius }
        points.count { maxRadius.inRange(it) }
    }
}

class Point3D {

    static final Pattern POINT3D_MATCHER = Pattern.compile(/pos=<(-?\d+),(-?\d+),(-?\d+)>, r=(-?\d+)/)
    int x, y, z
    int radius

    static Point3D from(String line) {
        def matcher = line =~ POINT3D_MATCHER
        if (!matcher) throw new RuntimeException("Invalid point $line")
        new Point3D(
                x: matcher[0][1].toInteger(),
                y: matcher[0][2].toInteger(),
                z: matcher[0][3].toInteger(),
                radius: matcher[0][4].toInteger())
    }

    int distance(Point3D point3D) {
        Math.abs(x - point3D.x) + Math.abs(y - point3D.y) + Math.abs(z - point3D.z)
    }

    boolean inRange(Point3D point3D) {
        distance(point3D) <= radius
    }

    @Override
    String toString() {
        "[$x,$y,$z]"
    }

    boolean equals(final o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Point3D point4D = (Point3D) o

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