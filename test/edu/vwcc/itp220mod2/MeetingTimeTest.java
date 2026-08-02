package edu.vwcc.itp220mod2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Instructions: Use these tests to check MeetingTime.overlaps().
 */
class MeetingTimeTest {

    private MeetingTime mt(Set<DayOfWeek> days, int startHour, int startMin,
            int endHour, int endMin) {
        return new MeetingTime(days, LocalTime.of(startHour, startMin),
                LocalTime.of(endHour, endMin));
    }

    @Test
    void overlapsWhenSharedDayAndTimesCross() {
        MeetingTime mwf = mt(Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY), 10, 0, 10, 50);
        MeetingTime mw = mt(Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY), 10, 30, 11, 20);
        assertTrue(mwf.overlaps(mw));
        assertTrue(mw.overlaps(mwf));
    }

    @Test
    void overlapsWhenOneIntervalContainsTheOther() {
        MeetingTime lab = mt(Set.of(DayOfWeek.THURSDAY), 9, 0, 12, 0);
        MeetingTime lecture = mt(Set.of(DayOfWeek.THURSDAY), 10, 0, 10, 50);
        assertTrue(lab.overlaps(lecture));
        assertTrue(lecture.overlaps(lab));
    }

    @Test
    void daysMatterNotJustTimes() {
        MeetingTime mw = mt(Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY), 10, 0, 10, 50);
        MeetingTime tr = mt(Set.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY), 10, 0, 10, 50);
        MeetingTime mOnly = mt(Set.of(DayOfWeek.MONDAY), 10, 0, 10, 50);
        assertFalse(mw.overlaps(tr));
        assertTrue(mw.overlaps(mOnly));
    }

    @Test
    void backToBackDoesNotOverlap() {
        MeetingTime first = mt(Set.of(DayOfWeek.MONDAY), 10, 0, 10, 50);
        MeetingTime second = mt(Set.of(DayOfWeek.MONDAY), 10, 50, 11, 40);
        MeetingTime crossing = mt(Set.of(DayOfWeek.MONDAY), 10, 30, 11, 30);
        assertFalse(first.overlaps(second));
        assertFalse(second.overlaps(first));
        assertTrue(second.overlaps(crossing));
    }
}
