package edu.vwcc.itp220mod2;

import java.time.LocalDateTime;

/**
 * The link between one Student and one Section.
 *
 * Why does the link get its own class? Because the link itself knows
 * things: its status, and when it happened. If Section just kept a
 * List<Student>, there would be nowhere to write "waitlisted" or "dropped
 * on October 3rd". In UML this is called an ASSOCIATION CLASS; turning a
 * many-to-many link into an object like this is the standard move, and it
 * is exactly the shape of a row in a registration database table.
 *
 * Objects of this class are created by Section.enroll() and nowhere else
 * -- notice the constructor below is package-private.
 *
 * Instructions:
 * Implement isActive().
 */
public class Enrollment {

    private final Student student;
    private final Section section;
    private final LocalDateTime enrolledAt;
    private EnrollmentStatus status; // not final -- this one can change

    /**
     * Package-private: an Enrollment is born inside Section.enroll(),
     * never from outside the package. Already done for you.
     */
    Enrollment(Student student, Section section, EnrollmentStatus status) {
        this.student = student;
        this.section = section;
        this.status = status;
        this.enrolledAt = LocalDateTime.now();
    }

    public Student getStudent() {
        return student;
    }

    public Section getSection() {
        return section;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    /**
     * An enrollment is active while the student is still in the picture:
     * ENROLLED and WAITLISTED are active, DROPPED is not.
     *
     * @return true unless this enrollment has been dropped
     */
    public boolean isActive() {
        // TODO: your code goes here
        return false;
    }

    /**
     * Package-private: only Section changes a status, during drop() and
     * waitlist promotion. Already done for you.
     */
    void setStatus(EnrollmentStatus newStatus) {
        this.status = newStatus;
    }
}
