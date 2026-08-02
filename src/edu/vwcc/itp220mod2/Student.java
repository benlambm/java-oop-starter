package edu.vwcc.itp220mod2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A student: IS-A Person, and HAS enrollments linking them to Sections.
 *
 * Student never holds a List<Section> directly. The link to a Section runs
 * through an Enrollment object, because the link itself carries facts
 * (status, timestamp) -- see Enrollment.java.
 *
 * Instructions:
 * Implement role() and schedule().
 */
public class Student extends Person {

    // This end of the Student <-> Section association. Section.enroll()
    // keeps it in sync with the Section's own list -- see addEnrollment.
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Student(String id, String name, String email) {
        // super(...) runs the Person constructor first, so the inherited
        // fields exist before this subclass does anything of its own.
        super(id, name, email);
    }

    /**
     * @return exactly "Student"
     */
    @Override
    public String role() {
        // TODO: your code goes here
        return "";
    }

    /**
     * The sections this student actually has a seat in -- ENROLLED only.
     * Waitlisted is not on your schedule yet; dropped is not on it anymore.
     *
     * Loop over the enrollments, keep e.getSection() for each one whose
     * status is EnrollmentStatus.ENROLLED, in list order.
     *
     * @return a new list of enrolled-in sections (possibly empty)
     */
    public List<Section> schedule() {
        // TODO: your code goes here
        return new ArrayList<>();
    }

    /**
     * A read-only view of this student's enrollments. Already done for
     * you: handing out the real list would let any caller bypass
     * Section.enroll() and corrupt the model with list.add(...).
     */
    public List<Enrollment> enrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    /**
     * No "public" or "private" on this one -- that makes it PACKAGE-
     * PRIVATE: only code in edu.vwcc.itp220mod2 can call it. That is
     * exactly one caller, Section.enroll(), which uses it to keep both
     * ends of the association in sync. Outside code cannot fake an
     * enrollment. Already done for you.
     */
    void addEnrollment(Enrollment e) {
        enrollments.add(e);
    }
}
