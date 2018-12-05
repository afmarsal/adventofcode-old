import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.regex.Matcher

import static Day4.RecordType.*

class Day4 {

    static final Closure<Integer> SUM = { v1, v2 -> v1 + v2 }

    static def test1(String input) {

        List records = input.readLines().collect { Record.from(it) }
        records.sort(true)

        int guardId = getGuardSleepingTheMost(records)

        def minute = getMostSleptMinute(records, guardId)

        guardId * minute
    }

    static def test2(String input) {

        List records = input.readLines().collect { Record.from(it) }
        records.sort(true)

        topMinuteAndGuard(records)
    }

    static int topMinuteAndGuard(final List<Record> records) {
        Map<Integer, Integer> sleeps = [:]
        int currentGuardId
        LocalDateTime asleepAt
        for (Record record : records) {
            record.with {
                switch (type) {
                    case SHIFT:
                        currentGuardId = guardId
                        break
                    case ASLEEP:
                        asleepAt = dt
                        break
                    case WAKE_UP:
                        asleepAt.upto(dt.minusMinutes(1), ChronoUnit.MINUTES, {
                            sleeps.merge(it.minute, [(currentGuardId): 1], { m1, m2 ->
                                if (m1.containsKey(currentGuardId)) {
                                    m1[(currentGuardId)] += 1
                                } else {
                                    m1 << m2
                                }
                                m1
                            })
                        })
                        break
                }
            }
        }

        def maxSleepTimes = -1
        def minute = -1
        def guardId = -1
        sleeps.each { int k, Map v ->
            def guardAndTimeEntry = v.max { it.value }
            if (maxSleepTimes < guardAndTimeEntry.value) {
                maxSleepTimes = guardAndTimeEntry.value
                guardId = guardAndTimeEntry.key
                minute = k
            }
        }
        minute * guardId
    }

    private static int getGuardSleepingTheMost(List<Record> records) {
        Map<Integer, Integer> totalSleep = [:]
        int currentGuardId
        LocalDateTime asleepAt
        for (Record record : records) {
            record.with {
                switch (type) {
                    case SHIFT:
                        currentGuardId = guardId
                        break
                    case ASLEEP:
                        guardId = currentGuardId
                        asleepAt = dt
                        break
                    case WAKE_UP:
                        guardId = currentGuardId
                        totalSleep.merge(currentGuardId, (int) ChronoUnit.MINUTES.between(asleepAt, dt), SUM)
                        break
                }
            }
        }

        totalSleep.max { it.value }.key
    }

    static int getMostSleptMinute(List<Record> records, final int guardId) {
        Map<Integer, Integer> minutes = [:]
        LocalDateTime asleepAt
        records.findAll { it.guardId == guardId }.each { record ->
            record.with {
                switch (type) {
                    case ASLEEP:
                        asleepAt = dt
                        break
                    case WAKE_UP:
                        asleepAt.upto(dt.minusMinutes(1), ChronoUnit.MINUTES, {
                            minutes.merge(it.minute, 1, SUM)
                        })
                        break
                }
            }
        }
        minutes.max { it.value }.key
    }

    static class Record implements Comparable<Record> {

        static final def SHIFT_PATTERN = ~/\[(.*)\] Guard #(\d+) begins shift/
        static final def ASLEEP_PATTERN = ~/\[(.*)\] falls asleep/
        static final def WAKE_UP_PATTERN = ~/\[(.*)\] wakes up/

        LocalDateTime dt
        int guardId
        RecordType type

        static Record from(String line) {
            switch (line) {
                case SHIFT_PATTERN:
                    return new Record(dt: parseDateTime(),
                            guardId: Matcher.lastMatcher[0][2].toInteger(),
                            type: SHIFT)

                case ASLEEP_PATTERN:
                    return new Record(dt: parseDateTime(),
                            guardId: -1,
                            type: ASLEEP)

                case WAKE_UP_PATTERN:
                    return new Record(dt: parseDateTime(),
                            guardId: -1,
                            type: WAKE_UP)

                default:
                    throw new RuntimeException("Invalid line ${line}")
            }
        }

        private static LocalDateTime parseDateTime() {
            LocalDateTime.parse(Matcher.lastMatcher[0][1], "yyyy-MM-dd HH:mm")
        }


        @Override
        String toString() {
            final StringBuilder sb = new StringBuilder("Record{");
            sb.append("dt=").append(dt);
            sb.append(", guardId=").append(guardId);
            sb.append(", type=").append(type);
            sb.append('}');
            return sb.toString();
        }

        @Override
        int compareTo(final Record o) {
            def result = dt <=> o.dt
            if (result == 0) {
                throw new RuntimeException("Two records have the same date!")
            }
            result
        }
    }

    enum RecordType {
        SHIFT, ASLEEP, WAKE_UP
    }
}
