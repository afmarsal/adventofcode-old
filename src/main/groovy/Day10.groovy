class Day10 {


    static def test1(String input) {

        List<Point> points = input.readLines().collect {
            def m = it =~ /position=<\s*(-?\d+),\s*(-?\d+)> velocity=<\s*(-?\d+),\s*(-?\d+)>/
            if (!m) throw new RuntimeException("Invalid line: ${it}")
            new Point(*(m[0][1..4]))
        }

        int i = 0
        while (true) {
            Set<Point> allPoints = new HashSet<>(points)
            def allHaveAdjacent = allPoints.every { hasAnyAdjacent it, allPoints }
            if (allHaveAdjacent) {
                printPoints allPoints
                break
            }
            points.each { it.move() }
            ++i
        }

        i
    }

    static def printPoints(final Set<Point> points) {
        def minX = points.min { it.x }.x
        def maxX = points.max { it.x }.x
        def minY = points.min { it.y }.y
        def maxY = points.max { it.y }.y

        for (int j = minY; j <= maxY; ++j) {
            for (int i = minX; i <= maxX; ++i) {
                if (new Point(i, j) in points) {
                    print "#"
                } else {
                    print "."
                }
            }
            println ""
        }
    }

    static boolean hasAnyAdjacent(Point point, Set<Point> allPoints) {
        Set<Point> adjacents = []
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    adjacents << new Point(point.x + i, point.y + j)
                }
            }
        }
        adjacents.any { allPoints.contains(it) }
    }

    static class Point {
        int x
        int y
        int vx
        int vy

        Point(final String x, final String y, final String vx, final String vy) {
            this.x = x.toInteger()
            this.y = y.toInteger()
            this.vx = vx.toInteger()
            this.vy = vy.toInteger()
        }

        Point(final int x, final int y) {
            this.x = x
            this.y = y
            this.vx = 0
            this.vy = 0
        }

        void move() {
            x += vx
            y += vy
        }

        boolean equals(final o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            Point point = (Point) o

            if (x != point.x) return false
            if (y != point.y) return false

            return true
        }

        int hashCode() {
            int result
            result = x
            result = 31 * result + y
            return result
        }


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Point{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append(", vx=").append(vx);
            sb.append(", vy=").append(vy);
            sb.append('}');
            return sb.toString();
        }
    }
}
