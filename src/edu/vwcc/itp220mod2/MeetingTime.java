package edu.vwcc.itp220mod2;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

/**
 * NEW CONCEPT -- record.
 *
 * A record is a class for plain immutable data. This one line:
 *
 *     public record MeetingTime(Set<DayOfWeek> days, LocalTime start, LocalTime end)
 *
 * gives you, for free: private final fields, a constructor, accessors named
 * after the components (days(), start(), end() -- note: NOT getDays()),
 * plus equals(), hashCode(), and toString() that compare by value. Written
 * as an ordinary class, that is forty lines of boilerplate.
 *
 * Use a record when a thing IS its data: two MeetingTimes with the same
 * days and times are the same meeting time. No identity, no setters, no
 * changing after construction. Objects like this are called VALUE OBJECTS.
 *
 * A record can still declare methods (see overlaps below) and can validate
 * in a "compact constructor" -- the body below runs before the fields are
 * assigned, so bad values never make it into an object.
 *
 * Instructions:
 * Implement overlaps(). A MeetingTime is a weekly pattern such as
 * "MWF 10:00-10:50". Section owns a list of these -- composition: they are
 * created with the section and have no life outside it.
 */
public record MeetingTime(Set<DayOfWeek> days, LocalTime start, LocalTime end) {

    /**
     * Compact constructor: validation and defensive copying, already done
     * for you. Set.copyOf makes an immutable copy, so no caller can keep a
     * reference to our day set and change it behind our back.
     */
    public MeetingTime {
        if (days == null || days.isEmpty()) {
            throw new IllegalArgumentException("a meeting time needs at least one day");
        }
        if (start == null || end == null || !start.isBefore(end)) {
            throw new IllegalArgumentException("start must be before end");
        }
        days = Set.copyOf(days);
    }

    /**
     * Two weekly patterns overlap when BOTH are true:
     *   1. they share at least one day (loop over this.days and ask
     *      other.days().contains(...)), AND
     *   2. their time intervals cross: this starts before the other ends,
     *      and the other starts before this ends.
     *
     * Back-to-back does NOT overlap: a 10:00-10:50 class and a 10:50-11:40
     * class can both be attended. LocalTime has isBefore() -- comparing
     * times by hand is how off-by-one-minute bugs are born.
     *
     * @param other the meeting time to compare against
     * @return true when the two patterns collide
     */
    public boolean overlaps(MeetingTime other) {
        // TODO: your code goes here
        return false;
    }
}
