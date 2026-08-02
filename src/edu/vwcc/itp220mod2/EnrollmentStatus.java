package edu.vwcc.itp220mod2;

/**
 * NEW CONCEPT -- enum.
 *
 * An enum is a type whose complete set of values you list right here, by
 * name. A variable of type EnrollmentStatus can hold ENROLLED, WAITLISTED,
 * or DROPPED -- nothing else. The compiler enforces it.
 *
 * Before enums, code used Strings ("enrolled"? "Enrolled"? "ENROLED"?) or
 * int codes (what does 2 mean again?). Both compile happily when wrong.
 * An enum makes the illegal values impossible to write.
 *
 * You compare enum values with == (there is exactly one ENROLLED object in
 * the whole program), for example:
 *
 *     if (e.getStatus() == EnrollmentStatus.ENROLLED) { ... }
 *
 * Nothing to implement in this file -- read it, then use it.
 */
public enum EnrollmentStatus {

    /** Has a seat in the section. Counts against capacity. */
    ENROLLED,

    /** Wants a seat, section was full. Does NOT count against capacity. */
    WAITLISTED,

    /** Was enrolled or waitlisted, then dropped. Kept for the record. */
    DROPPED
}
