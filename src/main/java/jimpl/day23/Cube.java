package jimpl.day23;

public class Cube {

    private final int minX, minY, minZ;
    private final int maxX, maxY, maxZ;

    private Cube(final int minX, final int minY, final int minZ, final int maxX, final int maxY, final int maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public static Cube cubeOf(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return new Cube(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMinZ() {
        return minZ;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public Point3D firstPoint() {
        return Point3D.from(minX, minY, minZ);
    }

    private int centerX() {
        return getMinX() + ((getMaxX() - getMinX()) / 2);
    }

    private int centerY() {
        return getMinY() + ((getMaxY() - getMinY()) / 2);
    }

    private int centerZ() {
        return getMinZ() + ((getMaxZ() - getMinZ()) / 2);
    }

    public Point3D getCenter() {
        return Point3D.from(centerX(), centerY(),centerZ());
    }


    @Override
    public String toString() {
        return String.format("[%d,%d,%d]-[%d,%d,%d]", minX, minY, minZ, maxX, maxY, maxZ);
    }
}
