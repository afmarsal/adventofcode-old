package jimpl.day23;

import java.util.Objects;

class Point3D {
    final int x;
    final int y;
    final int z;

    protected Point3D(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Point3D from(int x, int y, int z) {
        return new Point3D(x, y, z);
    }

    public int distance(final Point3D point3D) {
        return Math.abs(x - point3D.x) + Math.abs(y - point3D.y) + Math.abs(z - point3D.z);
    }

    public Point3D offsetX(final int offsetX) {
        return new Point3D(x + offsetX, y, z);
    }

    public Point3D offsetY(final int offsetY) {
        return new Point3D(x, y + offsetY, z);
    }

    public Point3D offsetZ(final int offsetZ) {
        return new Point3D(x, y, z + offsetZ);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return x == point3D.x &&
                y == point3D.y &&
                z == point3D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return String.format("{%d,%d,%d}", x, y, z);
    }


}
