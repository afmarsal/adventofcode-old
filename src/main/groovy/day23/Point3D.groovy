package day23

class Point3D {
    int x, y, z

    static Point3D from(x, y, z) {
        new Point3D(x: x, y: y, z: z)
    }

    int distance(Point3D point3D) {
        Math.abs(x - point3D.x) + Math.abs(y - point3D.y) + Math.abs(z - point3D.z)
    }

    boolean equals(final o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Point3D that = (Point3D) o

        if (x != that.x) return false
        if (y != that.y) return false
        if (z != that.z) return false

        return true
    }

    int hashCode() {
        int result
        result = x
        result = 31 * result + y
        result = 31 * result + z
        return result
    }

    @Override
    String toString() {
        "{$z,$y,$x}"
    }
}