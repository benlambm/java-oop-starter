package edu.vwcc.itp220mod2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Instructions: Use these tests to check Course.addSection(), sections(),
 * and findSection().
 */
class CourseTest {

    private Course itp220() {
        return new Course("ITP 220", "Java Programming II", 4);
    }

    @Test
    void addSectionCreatesASectionOwnedByThisCourse() {
        Course course = itp220();
        Section s = course.addSection("40001", 25, new OnlineAsync(), new ArrayList<MeetingTime>());
        assertNotNull(s, "addSection should return the Section it created");
        assertEquals("40001", s.getCrn());
        assertSame(course, s.getCourse(), "the new Section should know its owning Course");
        assertEquals(1, course.sections().size());
        assertSame(s, course.sections().get(0));
    }

    @Test
    void sectionsIsAReadOnlyView() {
        Course course = itp220();
        course.addSection("40001", 25, new OnlineAsync(), new ArrayList<MeetingTime>());
        try {
            course.sections().add(null);
            fail("writing through sections() should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException expected) {
            // correct: the only door into the sections list is addSection()
        }
    }

    @Test
    void findSectionMatchesByCrnOrReturnsNull() {
        Course course = itp220();
        Section first = course.addSection("40001", 25, new OnlineAsync(), new ArrayList<MeetingTime>());
        Section second = course.addSection("40002", 25, new OnlineAsync(), new ArrayList<MeetingTime>());
        assertNotNull(first, "addSection should return the Section it created");
        assertSame(second, course.findSection("40002"));
        assertSame(first, course.findSection("40001"));
        assertNull(course.findSection("99999"), "an unknown CRN should return null");
    }
}
