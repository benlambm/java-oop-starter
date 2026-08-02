package edu.vwcc.itp220mod2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * One actual offering of a Course: a CRN, seats, meeting times, maybe an
 * instructor, and the enrollments that fill it. This is the busiest class
 * in the model -- most of the module's ideas meet here:
 *
 *   COMPOSITION  this Section owns its MeetingTimes and its Enrollments;
 *                they are created with it and die with it.
 *   ASSOCIATION  the Instructor link: both objects exist independently
 *                and just point at each other.
 *   DERIVED STATE  seatsRemaining() and isOpen() are COMPUTED from the
 *                enrollments list every time. There is no "seats" field
 *                to forget to update -- storage you can derive is a bug
 *                you have scheduled for later.
 *
 * Instructions:
 * Implement the eight TODO methods. Suggested order: instructorName,
 * assignInstructor, seatsRemaining, isOpen, enroll, waitlist, drop,
 * conflictsWith. The tests in SectionTest.java are the precise spec.
 */
public class Section {

    private final Course course;
    private final String crn;
    private final int capacity;
    private final Modality modality;
    private final List<MeetingTime> meetingTimes;
    private final List<Enrollment> enrollments = new ArrayList<>();

    // Not final, and starts null: real schedules list sections as "Staff"
    // until a human is assigned.
    private Instructor instructor;

    /**
     * Package-private ON PURPOSE: only Course.addSection() may create a
     * Section. That is composition enforced by the compiler. Already done
     * for you -- note List.copyOf, which snapshots the meeting times into
     * an immutable list so nobody can edit our schedule from outside.
     */
    Section(Course course, String crn, int capacity, Modality modality,
            List<MeetingTime> meetingTimes) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.course = course;
        this.crn = crn;
        this.capacity = capacity;
        this.modality = modality;
        this.meetingTimes = List.copyOf(meetingTimes);
    }

    public Course getCourse() {
        return course;
    }

    public String getCrn() {
        return crn;
    }

    public int getCapacity() {
        return capacity;
    }

    public Modality getModality() {
        return modality;
    }

    /** Immutable already (List.copyOf in the constructor). */
    public List<MeetingTime> meetingTimes() {
        return meetingTimes;
    }

    /** Read-only view; the real list changes only via enroll/drop. */
    public List<Enrollment> enrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    /**
     * The instructor's name, or "Staff" when none is assigned yet.
     * One null check -- this method exists so no other code ever has to
     * remember that instructor can be null.
     *
     * @return the name, or "Staff"
     */
    public String instructorName() {
        // TODO: your code goes here
        return "";
    }

    /**
     * Assign the instructor. An association has two ends: set the field
     * on this side, then tell the instructor with i.addSection(this).
     * Forgetting the second half is THE classic association bug -- one
     * end thinks the link exists and the other end has never heard of it.
     *
     * @param i the instructor taking this section
     */
    public void assignInstructor(Instructor i) {
        // TODO: your code goes here
    }

    /**
     * How many seats are left: capacity minus the number of enrollments
     * whose status is ENROLLED. Waitlisted and dropped students do not
     * hold seats. Count with a loop -- do NOT add a seats field.
     *
     * @return remaining seats, 0 or more
     */
    public int seatsRemaining() {
        // TODO: your code goes here
        return 0;
    }

    /**
     * @return true while at least one seat remains
     */
    public boolean isOpen() {
        // TODO: your code goes here
        return false;
    }

    /**
     * Enroll a student. The one method where the whole model comes
     * together -- take it step by step:
     *
     *   1. Guard: loop over enrollments; if this student already has an
     *      ACTIVE enrollment here (use e.isActive()), throw
     *      new IllegalArgumentException("already enrolled: " + s.getName())
     *      A student who dropped earlier MAY re-enroll.
     *   2. Decide the status: ENROLLED if isOpen(), otherwise WAITLISTED.
     *   3. Create the Enrollment: new Enrollment(s, this, status).
     *   4. Wire BOTH ends of the association:
     *      add it to this.enrollments, and call s.addEnrollment(e).
     *   5. Return the new Enrollment.
     *
     * @param s the student registering
     * @return the new Enrollment (caller can check its status)
     * @throws IllegalArgumentException if s is already active here
     */
    public Enrollment enroll(Student s) {
        // TODO: your code goes here
        return null;
    }

    /**
     * Drop a student, and promote the next waitlisted student if a seat
     * just opened:
     *
     *   1. Find this student's ACTIVE enrollment (loop; isActive() and
     *      getStudent() == s). None found? throw
     *      new IllegalArgumentException("not enrolled: " + s.getName())
     *   2. Remember whether its status was ENROLLED, then set it to
     *      DROPPED (setStatus is package-private -- we may call it).
     *   3. If it WAS enrolled, a real seat opened: find the FIRST
     *      enrollment in list order with status WAITLISTED and promote it
     *      to ENROLLED. First come, first served -- list order is the
     *      fairness policy.
     *
     * @param s the student leaving
     * @throws IllegalArgumentException if s has no active enrollment here
     */
    public void drop(Student s) {
        // TODO: your code goes here
    }

    /**
     * Students who hold seats (status ENROLLED), in enrollment order.
     * Already done for you -- and it is the worked example for waitlist()
     * below: same loop, different status.
     */
    public List<Student> roster() {
        List<Student> enrolled = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStatus() == EnrollmentStatus.ENROLLED) {
                enrolled.add(e.getStudent());
            }
        }
        return enrolled;
    }

    /**
     * Students waiting for a seat (status WAITLISTED), in order.
     * Mirror roster() above.
     *
     * @return a new list of waitlisted students (possibly empty)
     */
    public List<Student> waitlist() {
        // TODO: your code goes here
        return new ArrayList<>();
    }

    /**
     * Could a student attend both this section and the other one?
     * Compare every MeetingTime of this section against every MeetingTime
     * of the other (nested loops) using overlaps(). An async section has
     * no meeting times, so it can never conflict -- the loops simply never
     * find a pair. No special case needed: empty lists do the right thing.
     *
     * @param other the section to check against
     * @return true if any two weekly patterns collide
     */
    public boolean conflictsWith(Section other) {
        // TODO: your code goes here
        return false;
    }
}
