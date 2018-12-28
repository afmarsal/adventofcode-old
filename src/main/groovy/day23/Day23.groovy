package day23


import static util.Logger.log

class Day23 {

    static def test1(String input) {

        def points = input.readLines().collect { Point3DAndRadius.from(it) }
        def maxRadius = points.max { it.radius }
        points.count { maxRadius.inRange(it) }
    }

//    static def test2(String input) {
//        def points = input.readLines().collect { Point3DAndRadius.from(it) }
//
//        TreeSet listOfZ = points.collectMany { [it.minZ, it.maxZ] }.toSet()
//
//        log listOfZ
//
//        def borders = [:]
//
//        def minZ = points.min { it.minZ }.minZ
//        def maxZ = points.max { it.maxZ }.maxZ
//        minZ.upto(maxZ) { currentZ ->
//
//            log "*** Processing z: $currentZ"
//
//            def pointsInZ = points.findAll { it.crossesZ(currentZ) }
////            TreeSet listOfY = pointsInZ.collectMany { [it.minY, it.maxY] }.toSet()
//            def minY = pointsInZ.min { it.minY }.minY
//            def maxY = pointsInZ.max { it.maxY }.maxY
//
//            minY.upto(maxY) { currentY ->
//
//                log "--- Processing y: $currentY"
//
//                def pointsInZAndY = pointsInZ.findAll { it.crossesY(currentY) }
//                SortedMap eventsByPoint = new TreeMap()
//                pointsInZAndY.each { Point3DAndRadius point ->
//                    eventsByPoint.merge(point.minX, [eventOf(START, point)], { l1, l2 -> l1 + l2 })
//                    eventsByPoint.merge(point.maxX, [eventOf(END, point)], { l1, l2 -> l1 + l2 })
//                }
//                log "Events at $currentZ,$currentY -> $eventsByPoint"
//                def activePoints = [] as Set
//                eventsByPoint.each { currentX, events ->
//                    List sortedEvents = events.sort(true)
//                    log "Processing event at $currentZ, $currentY, $currentX -> $sortedEvents"
//                    sortedEvents
//                            .takeWhile { it.eventType == START }
//                            .each { activePoints << it.point }
//                    def borderPoint = Point3D.from(currentX, currentY, currentZ)
//                    borders[borderPoint] = activePoints.size()
//                    sortedEvents
//                            .dropWhile { it.eventType == START }
//                            .each { assert it.point in activePoints; activePoints.remove it.point }
//                }
//            }
//        }
//        log "Border points ->\n${borders.entrySet().join("\n")}"
//    }

    static def test2(String input) {
        def points = input.readLines().collect { Point3DAndRadius.from(it) }

        def minZ = points.min { it.minZ }.minZ
        def maxZ = points.max { it.maxZ }.maxZ

        def pointsAtZ = [:]
        for (int z = minZ; z <= maxZ; ++z) {
            pointsAtZ[z] = points.findAll { it.crossesZ(z) }
        }
        def mostMatchedZ = pointsAtZ.max { it.value.size() }.value.size()
        def pointsWithMostMatchesInZ = pointsAtZ.findAll { it.value.size() == mostMatchedZ }
        log "**** Points at z with most matches: \n${pointsWithMostMatchesInZ.entrySet().join("\n")}"

        def pointsWithMostMatches = [:]

        pointsWithMostMatchesInZ.each { z, List<Point3DAndRadius> pointsInZ ->
            def minY = pointsInZ.min { it.minY }.minY
            def maxY = pointsInZ.max { it.maxY }.maxY

            def pointsAtY = [:]
            for (int y = minY; y <= maxY; y++) {
                pointsAtY[y] = pointsInZ.findAll { it.crossesY(y) }
            }
            def mostMatchedY = pointsAtY.max { it.value.size() }.value.size()
            def pointsWithMostMatchesInY = pointsAtY.findAll { it.value.size() == mostMatchedY }
            log "==== Points at $z with most matches in Y: \n${pointsWithMostMatchesInY.entrySet().join("\n")}"

            pointsWithMostMatchesInY.each { y, List<Point3DAndRadius> pointsInY ->
                def minX = pointsInY.min { it.minX }.minX
                def maxX = pointsInY.max { it.maxX }.maxX

                def pointsAtX = [:]
                for (int x = minX; x <= maxX; x++) {
                    Point3D referencePoint = Point3D.from(x, y, z)
                    pointsAtX[x] = pointsInY.findAll { it.distance(referencePoint) <= it.radius }
                }
                def mostMatchedX = pointsAtX.max { it.value.size() }.value.size()
                def pointsWithMostMatchesInX = pointsAtX.findAll { it.value.size() == mostMatchedX }

                log "---- Points at $z,$y with most matches in x: \n${pointsWithMostMatchesInX.entrySet().join("\n")}"
                pointsWithMostMatchesInX.each { x, pointsInX ->
                    def currentPoint = Point3D.from(x, y, z)
                    pointsWithMostMatches.merge(currentPoint, pointsInX.size(), { v1, v2 -> Math.max(v1, v2) })
                }
            }
        }
        log "Max points\n${pointsWithMostMatches.entrySet().join("\n")}"
        def mostMatchedTimes = pointsWithMostMatches.max { it.value }.value
        def mostMatchedPoints = pointsWithMostMatches.findAll { it.value == mostMatchedTimes }.collect { it.key }
        def myLocation = Point3D.from(0, 0, 0)
        mostMatchedPoints.min { it.distance(myLocation) }.distance(myLocation)
    }

}

//
//enum EventType {
//    START, END
//}
//
//class XEvent implements Comparable<XEvent> {
//    EventType eventType
//    Point3DAndRadius point
//
//    static XEvent eventOf(EventType eventType, Point3DAndRadius point) {
//        new XEvent(eventType: eventType, point: point)
//    }
//
//    boolean equals(final o) {
//        if (this.is(o)) return true
//        if (getClass() != o.class) return false
//
//        XEvent xEvent = (XEvent) o
//
//        if (eventType != xEvent.eventType) return false
//        if (point != xEvent.point) return false
//
//        return true
//    }
//
//    int hashCode() {
//        int result
//        result = (eventType != null ? eventType.hashCode() : 0)
//        result = 31 * result + (point != null ? point.hashCode() : 0)
//        return result
//    }
//
//    @Override
//    public String toString() {
//        "{$eventType,$point}"
//    }
//
//    @Override
//    int compareTo(final XEvent o) {
//        def result = eventType <=> o.eventType
//        if (result != 0) return result
//        point <=> o.point
//    }
