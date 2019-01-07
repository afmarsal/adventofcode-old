package jimpl.day23;


import day20.Node;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Comparator.comparing;
import static jimpl.day23.Logger.log;

public class Day23Java {

    private static final Point3D SELF_LOCATION = Point3D.from(0, 0, 0);

//    public static int test2(final String input) {
//        Node3d[] points = parsePoints(input);
//        TreeMap<Integer, Integer> ranges = new TreeMap<>();
//        for (Node3d n : points) {
//            int distFromZero = n.distance(new Node3d(0, 0, 0, 0));
//            ranges.put(Math.max(0, distFromZero -  (int) n.range), 1);
//            ranges.put(distFromZero + (int) n.range, -1);
//        }
//        int count = 0;
//        int result = 0;
//        int maxCount = 0;
//        for (Map.Entry<Integer, Integer> each : ranges.entrySet()) {
//            count += each.getValue();
//            if (count > maxCount) {
//                result = each.getKey();
//                maxCount = count;
//            }
//        }
//        return result;
//
//    }
    public static int test2(final String input) {
        Point3DAndRadius[] points = parsePoints(input);

        Collection<Set<Point3DAndRadius>> linkedPoints = buildLinkedPoints(points);

//        log("Linked points: %s", linkedPoints.toString());

        Set<Point3DAndRadius> commonPoints = findBiggestGroupOfIntersectingPoints(linkedPoints);
        log("Common points group: %d", commonPoints.size());

        // The point must be in the sphere of this point
//        Point3D thePoint = getPointInSphereContainedInAll(commonPoints, farthestPoint);


//        Point3D thePoint = commonPoints.stream()
//                .map(Point3DAndRadius::getClosestTo0)
//                .peek(p -> log("Closest to 0 point: %s", p))
//                .filter(p -> commonPoints.stream().allMatch(other -> other.inRange(p)))
//                .findAny()
//                .get();


//        Cube cube = surroundingCubeFor(commonPoints);
//        log("Surrounding cube %s, center at %s", cube, cube.getCenter());
//        Point3D thePoint = null;

//
//        Point3D thePoint = findClosestPointInCommon(commonPoints,
//                                                                Integer.MAX_VALUE,
//                                                                cube,
//                                                                singleton(cube.firstPoint()));

        return getTheDistance(commonPoints);

    }

//    private static Point3D getPointInSphereContainedInAll(final Set<Point3DAndRadius> points,
//                                                          final Point3DAndRadius startingPoint) {
//        Set<Point3D> pointsToCheck = new HashSet<>(singleton(startingPoint.getClosestTo0()));
//        while (!pointsToCheck.isEmpty()) {
//            Set<Point3D> nextPoints = new HashSet<>();
//            for (Point3D pointToCheck : pointsToCheck) {
//                if (points.stream().allMatch(point -> point.inRange(pointToCheck))) {
//                    return pointToCheck;
//                }
//                nextPoints.addAll(startingPoint.adjacentFor(pointToCheck));
//            }
//            // Try adjacent points in sphere
//
//        }
//        return null;
//    }

    private static int getTheDistance(final Set<Point3DAndRadius> commonPoints) {
        return commonPoints.stream()
                .mapToInt(point -> point.distance(SELF_LOCATION) - point.getRadius())
                .map(d -> d < 0 ? 0 : d)
                .max()
                .orElseThrow(RuntimeException::new);
    }

    private static Set<Point3DAndRadius> findBiggestGroupOfIntersectingPoints(final Collection<Set<Point3DAndRadius>> linkedPoints) {

        int startingSize = findStartingSize(linkedPoints);
        log("Starting size: %d", startingSize);

        Set<Point3DAndRadius> commonPoints = null;
        outer:
        for (int currentSize = startingSize; currentSize >= 0; --currentSize) {
            log("Processing size: %d", currentSize);
            // Intersect sets. If intersection size is less than size, discard intersection
            inner:
            for (Set<Point3DAndRadius> set1 : linkedPoints) {
                log("Processing set with size: %d", set1.size());
                commonPoints = new HashSet<>(set1);
                int discardedSets = 0;
                for (Set<Point3DAndRadius> set2 : linkedPoints) {
                    if (set1 == set2) continue;
                    Set<Point3DAndRadius> intersection = intersect(commonPoints, set2);
                    if (intersection.size() >= currentSize) {
                        commonPoints = intersection;
                    } else {
                        ++discardedSets;
                        if (discardedSets + currentSize > linkedPoints.size()) {
                            log("Discarding set. Discarded sets: %d, currentSize: %d", discardedSets, currentSize);
                            break inner;
                        }
                    }
                }
                if (commonPoints.size() >= currentSize) {
                    break outer;
                }
            }
        }
        return commonPoints;
    }

    private static <T> Set<T> intersect(final Set<T> set1, final Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    /**
     * The starting size to start testing for groups of points must be such as at least these many groups of points
     * must have such a size
     */
    private static int findStartingSize(final Collection<Set<Point3DAndRadius>> linkedPoints) {
        int maxLinkedPoints = linkedPoints.stream().max(comparing(Set::size)).get().size();
        return maxLinkedPoints;
//        for (int aSize = maxLinkedPoints; aSize >= 0; --aSize) {
//            long count = linkedPoints.stream().filter(biggerThan(aSize)).count();
//            if (count >= aSize) return aSize;
//        }
//        throw new RuntimeException("Could not find a suitable size");
    }

    private static Predicate<Set<Point3DAndRadius>> biggerThan(final int aSize) {
        return s -> s.size() >= aSize;
    }

    private static Node3d[] parseNodes(final String input) {
        return Arrays.stream(input.split("\n"))
                .map(Node3d::from)
                .toArray(Node3d[]::new);
    }
    private static Point3DAndRadius[] parsePoints(final String input) {
        return Arrays.stream(input.split("\n"))
                .map(Point3DAndRadius::from)
                .toArray(Point3DAndRadius[]::new);
    }

    private static Collection<Set<Point3DAndRadius>> buildLinkedPoints(final Point3DAndRadius[] points) {
        Collection<Set<Point3DAndRadius>> result = new ArrayList<>(points.length);
        for (Point3DAndRadius point : points) {
            Set<Point3DAndRadius> linkedPoints = new HashSet<>();
            for (Point3DAndRadius otherPoint : points) {
                if (point.intersect(otherPoint)) {
                    linkedPoints.add(otherPoint);
                }
            }
            result.add(linkedPoints);
        }
        return result;
    }

    private static Cube surroundingCubeFor(final Set<Point3DAndRadius> points) {
        int startingX, startingY, startingZ, finishingX, finishingY, finishingZ;
        startingX = startingY = startingZ = Integer.MIN_VALUE;
        finishingX = finishingY = finishingZ = Integer.MAX_VALUE;
        for (Point3DAndRadius point : points) {
            if (point.getMinX() > startingX) startingX = point.getMinX();
            if (point.getMinY() > startingY) startingY = point.getMinY();
            if (point.getMinZ() > startingZ) startingZ = point.getMinZ();
            if (point.getMaxX() < finishingX) finishingX = point.getMaxX();
            if (point.getMaxY() < finishingY) finishingY = point.getMaxY();
            if (point.getMaxZ() < finishingZ) finishingZ = point.getMaxZ();
        }
        return Cube.cubeOf(startingX, startingY, startingZ, finishingX, finishingY, finishingZ);
    }

    private static Point3D findClosestPointInCommon(final Set<Point3DAndRadius> points,
                                                    final int maxDistanceToConsider,
                                                    final Cube cube,
                                                    final Set<Point3D> pointsToAnalyze) {

        for (Point3D sweepPoint : pointsToAnalyze) {
            if (points.stream().allMatch(p -> p.inRange(sweepPoint))) {
                return sweepPoint;
            }
        }
        Set<Point3D> nextPoints = sweepPointsFor(cube, pointsToAnalyze);
        if (nextPoints.iterator().next().distance(SELF_LOCATION) >= maxDistanceToConsider) {
            return Point3DAndRadius.FARTHEST;
        }
        return findClosestPointInCommon(points, maxDistanceToConsider, cube, nextPoints);
    }

    private static Set<Point3D> sweepPointsFor(final Cube cube, final Set<Point3D> points) {
        Set<Point3D> result = new HashSet<>();
        for (Point3D point : points) {
            result.add(point.offsetX(1));
            result.add(point.offsetY(1));
            result.add(point.offsetZ(1));
        }
        return result;
    }

//    public static int test2(final String input) {
//        Point3DAndRadius[] points = Arrays.stream(input.split("\n"))
//                .map(Point3DAndRadius::from)
//                .toArray(Point3DAndRadius[]::new);
//
//        return findMostCoveredAndClosestPoint(points).distance(SELF_LOCATION);
//    }
//
//    /**
//     * For a group of points to intersect, they must intersect 2 by 2
//     * Start trying to intersect all points,
//     * then all combinations of all points minus 1,
//     * then all combinations of all points minus 2...
//     *
//     * @param points
//     */
//    private static Point3D findMostCoveredAndClosestPoint(final Point3DAndRadius[] points) {
//
//        log("Finding closest for %d points", points.length);
//
//        // This first call to the recursive method excludes the case where ALL points match, but that won't happen, right? :)
//        List<Set<Point3DAndRadius>> setOfIntersectingPoints = findGreatestSetOfIntersectingPoints(points, initialCombination(points.length));
//
//        int minDistance = Integer.MAX_VALUE;
//        Point3D closestPoint = null;
//        for (Set<Point3DAndRadius> intersectingPoints : setOfIntersectingPoints) {
//            Point3D point = findClosestPointInRange(intersectingPoints, minDistance);
//            if (point.distance(SELF_LOCATION) < minDistance) {
//                minDistance = point.distance(SELF_LOCATION);
//                closestPoint = point;
//            }
//        }
//        return closestPoint;
//    }
//
//    private static List<Set<Point3DAndRadius>> findGreatestSetOfIntersectingPoints(final Point3DAndRadius[] points,
//                                                                                   final Set<Combination> excludedCombinations) {
//        log("Finding greatest set of points with exclusions of size: %d", excludedCombinations.size());
//        List<Set<Point3DAndRadius>> result = new ArrayList<>();
//        for (Combination combination : excludedCombinations) {
//            if (match2By2(points, combination)) {
//                result.add(applyCombination(points, combination));
//            }
//        }
//        if (result.isEmpty()) {
//            Set<Combination> newExcludedCombinations = addNewDimension(excludedCombinations, points.length);
//            result = findGreatestSetOfIntersectingPoints(points, newExcludedCombinations);
//        }
//        log("Found greatest set of points: %s , with exclusions: %d", result, excludedCombinations.size());
//        return result;
//    }
//
//    /**
//     * Finds the enclosing cube for this set of points, and then applies brute force to find the
//     * closest point contained in all point areas
//     */
//    private static Point3D findClosestPointInRange(final Set<Point3DAndRadius> points,
//                                                   final int maxDistanceToConsider) {
//        Cube surroundingCommonCube = surroundingCubeFor(points);
//
//        return findClosestPointInCommon(points, maxDistanceToConsider, surroundingCommonCube, singleton(surroundingCommonCube.firstPoint()));
//    }
//
//    private static Point3D findClosestPointInCommon(final Set<Point3DAndRadius> points,
//                                                    final int maxDistanceToConsider,
//                                                    final Cube cube,
//                                                    final Set<Point3D> pointsToAnalyze) {
//        for (Point3D sweepPoint : pointsToAnalyze) {
//            if (points.stream().allMatch(p -> p.inRange(sweepPoint))) {
//                return sweepPoint;
//            }
//        }
//        Set<Point3D> nextPoints = sweepPointsFor(cube, pointsToAnalyze);
//        if (nextPoints.iterator().next().distance(SELF_LOCATION) >= maxDistanceToConsider) {
//            return Point3DAndRadius.FARTHEST;
//        }
//        return findClosestPointInCommon(points, maxDistanceToConsider, cube, nextPoints);
//    }
//
//    private static Set<Point3D> sweepPointsFor(final Cube cube, final Set<Point3D> points) {
//        Set<Point3D> result = new HashSet<>();
//        for (Point3D point : points) {
//            result.add(point.offsetX(1));
//            result.add(point.offsetY(1));
//            result.add(point.offsetZ(1));
//        }
//        return result;
//    }
//
//
//    private static Set<Point3DAndRadius> applyCombination(final Point3DAndRadius[] points,
//                                                          final Combination excludedPoints) {
//        Set<Point3DAndRadius> result = new HashSet<>();
//        for (int i = 0; i < points.length; i++) {
//            if (!excludedPoints.isSet(i)) {
//                result.add(points[i]);
//            }
//        }
//        return result;
//    }
//
//    private static boolean match2By2(final Point3DAndRadius[] points,
//                                     final Combination excluded) {
//        for (int i = 0; i < points.length - 1; ++i) {
//            if (excluded.isSet(i)) continue;
//            for (int j = i + 1; j < points.length; j++) {
//                if (excluded.isSet(j)) continue;
//                if (!points[i].intersect(points[j])) return false;
//            }
//        }
//        return true;
//    }
////    public static int test2(final String input) {
////        List<Point3DAndRadius> points = Arrays.stream(input.split("\n"))
////                .map(Point3DAndRadius::from)
////                .collect(toList());
////
////        List<Pair<Integer, CoordEvent>> sortedEventsInZ = points.stream()
////                .map(Day23Java::toStartAndEndEvents)
////                .flatMap(List::stream)
////                .sorted(byZAndThenEventType())
////                .collect(toList());
////
////        Map<Integer, Set<Point3D>> pointsAtZ = new LinkedHashMap<>();
////        Set<Point3D> activePoints = new HashSet<>();
////        int sortedEventsIdx = 0;
////        for (int z = sortedEventsInZ.get(0).getFirst(); z < sortedEventsInZ.get(sortedEventsInZ.size() - 1)
////                .getFirst(); z++) {
////            if ((z - sortedEventsInZ.get(0).getFirst()) % 100_000 == 0)
////                System.out.printf("Building points for %d%n", z);
////            while (z == sortedEventsInZ.get(sortedEventsIdx).getFirst()
////                    && sortedEventsInZ.get(sortedEventsIdx).getSecond().getEventType() == START) {
////                activePoints.add(sortedEventsInZ.get(sortedEventsIdx++).getSecond().getPoint());
////            }
////            pointsAtZ.put(z, new HashSet<>(activePoints));
////            while (z == sortedEventsInZ.get(sortedEventsIdx).getFirst()
////                    && sortedEventsInZ.get(sortedEventsIdx).getSecond().getEventType() == END) {
////                activePoints.remove(sortedEventsInZ.get(sortedEventsIdx++).getSecond().getPoint());
////            }
////        }
////        int maxPoints = pointsAtZ.entrySet().stream()
////                .max(comparing(e -> e.getValue().size())).get().getValue().size();
////        List<Map.Entry<Integer, Set<Point3D>>> pointsWithMostMatchesInZ = pointsAtZ.entrySet().stream()
////                .filter(e -> e.getValue().size() == maxPoints)
////                .collect(toList());
////
////        Map pointsWithMostMatches = new LinkedHashMap();
////
////        pointsWithMostMatchesInZ.forEach(e -> {
////            int z = e.getKey();
////            Set<Point3D> pointsInZ = e.getValue();
////
////            System.out.printf("Processing z = %d%n", z);
////
////        });
////        return 0;
////    }
//
//    private static Comparator<Pair<Integer, CoordEvent>> byZAndThenEventType() {
////        return Comparator.comparing(Pair::getFirst)
////                .thenComparing(p -> p.getSecond().getEventType());
//        return (pair1, pair2) -> {
//            int result = Integer.compare(pair1.getFirst(), pair2.getFirst());
//            if (result != 0) return result;
//            return pair1.getSecond().getEventType().compareTo(pair2.getSecond().getEventType());
//        };
//    }
//
//    private static List<Pair<Integer, CoordEvent>> toStartAndEndEvents(final Point3DAndRadius point) {
//        return asList(pairOf(point.getMinZ(), CoordEvent.eventOf(START, point)),
//                      pairOf(point.getMaxZ(), CoordEvent.eventOf(END, point)));
//    }

    static class Node3d {
        private static final Pattern POINT3D_MATCHER
                = Pattern.compile("pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(-?\\d+)");
        int x;
        int y;
        int z;
        long range;
        long getRange() { return range; }

        Node3d(int x, int y, int z, long range) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.range = range;
        }

        static Node3d from(String line) {
            Matcher matcher = POINT3D_MATCHER.matcher(line);
            if (!matcher.matches()) throw new RuntimeException("Invalid point " + line);
            return new Node3d(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)));
        }
        int distance(Node3d other) {
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + Math.abs(this.z - other.z);
        }

        boolean inRange(Node3d other) {
            return distance(other) <= other.range;
        }
    }

}
