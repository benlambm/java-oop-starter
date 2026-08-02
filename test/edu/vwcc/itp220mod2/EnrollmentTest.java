package edu.vwcc.itp220mod2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Instructions: Use this test to check Enrollment.isActive().
 *
 * This test builds Section and Enrollment objects directly through their
 * package-private constructors. It may: the test lives in the same
 * package. Production code cannot, and must go through Course.addSection()
 * and Section.enroll() -- that asymmetry is deliberate.
 */
class EnrollmentTest {

    @Test
    void activeUntilDropped() {
        Course course = new Course("ITP 220", "Java Programming II", 4);
        Section section = new Section(course, "40001", 10, new OnlineAsync(), new ArrayList<MeetingTime>());
        Student ada = new Student("s001", "Ada Lovelace", "ada@email.vccs.edu");

        Enrollment enrolled = new Enrollment(ada, section, EnrollmentStatus.ENROLLED);
        Enrollment waitlisted = new Enrollment(ada, section, EnrollmentStatus.WAITLISTED);

        assertTrue(enrolled.isActive(), "ENROLLED counts as active");
        assertTrue(waitlisted.isActive(), "WAITLISTED counts as active too");

        enrolled.setStatus(EnrollmentStatus.DROPPED);
        assertFalse(enrolled.isActive(), "DROPPED is no longer active");
    }
}
