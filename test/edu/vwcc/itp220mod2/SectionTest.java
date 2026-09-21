package edu.vwcc.itp220mod2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Instructions: Use these tests to check Section. They are the precise
 * spec for enroll, drop, the waitlist, seats, instructors, and conflicts.
 *
 * These tests build Sections directly through the package-private
 * constructor (allowed: same package) so that a bug in Course.addSection
 * cannot hide a bug here. Production code must go through Course.
 *
 * If a test here dies with a NullPointerException, the usual cause is
 * that enroll() still returns its placeholder null.
 */
class SectionTest {

    private Section sectionWithCapacity(int capacity) {
        Course course = new Course("ITP 220", "Java Programming II", 4);
        return new Section(course, "40001", capacity, new OnlineAsync(), new ArrayList<MeetingTime>());
    }

    private Student student(String name, String number) {
        return new Student("s" + number, name, "student" + number + "@email.vccs.edu");
    }

    @Test
    void enrollReturnsAnActiveEnrollment() {
        Section section = sectionWithCapacity(2);
        Student ada = student("Ada Lovelace", "001");
        Enrollment e = section.enroll(ada);
        assertNotNull(e, "enroll should return the new Enrollment");
        assertEquals(EnrollmentStatus.ENROLLED, e.getStatus());
        assertSame(ada, e.getStudent());
        assertSame(section, e.getSection());
    }

    @Test
    void enrollWiresBothEndsOfTheAssociation() {
        Section section = sectionWithCapacity(2);
        Student ada = student("Ada Lovelace", "001");
        Enrollment e = section.enroll(ada);
        assertNotNull(e, "enroll should return the new Enrollment");
        assertTrue(section.enrollments().contains(e), "the Section end should know about the link");
        assertTrue(ada.enrollments().contains(e), "the Student end should know about the link");
    }

    @Test
    void seatsRemainingCountsOnlyEnrolledStudents() {
        Section section = sectionWithCapacity(2);
        assertEquals(2, section.seatsRemaining(), "empty section: every seat free");
        section.enroll(student("Ada Lovelace", "001"));
        assertEquals(1, section.seatsRemaining());
        section.enroll(student("Alan Turing", "002"));
        assertEquals(0, section.seatsRemaining());
        section.enroll(student("Grace Hopper", "003")); // waitlisted
        assertEquals(0, section.seatsRemaining(), "a waitlisted student holds no seat");
    }

    @Test
    void openUntilTheLastSeatIsTaken() {
        Section section = sectionWithCapacity(1);
        assertTrue(section.isOpen(), "a fresh section has a free seat");
        section.enroll(student("Ada Lovelace", "001"));
        assertFalse(section.isOpen());
    }

    @Test
    void enrollingIntoAFullSectionWaitlists() {
        Section section = sectionWithCapacity(2);
        Student ada = student("Ada Lovelace", "001");
        Student alan = student("Alan Turing", "002");
        Student grace = student("Grace Hopper", "003");
        section.enroll(ada);
        section.enroll(alan);
        Enrollment third = section.enroll(grace);
        assertNotNull(third, "enroll should return the new Enrollment");
        assertEquals(EnrollmentStatus.WAITLISTED, third.getStatus());
        assertEquals(List.of(ada, alan), section.roster(), "the roster holds seat-holders only");
        assertEquals(List.of(grace), section.waitlist());
    }

    @Test
    void enrollingTwiceWhileActiveThrows() {
        Section section = sectionWithCapacity(2);
        Student ada = student("Ada Lovelace", "001");
        section.enroll(ada);
        try {
            section.enroll(ada);
            fail("a second enroll for the same active student should throw IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // correct
        }
    }

    @Test
    void dropFreesTheSeatAndKeepsTheRecord() {
        Section section = sectionWithCapacity(2);
        Student ada = student("Ada Lovelace", "001");
        Student alan = student("Alan Turing", "002");
        Enrollment adasEnrollment = section.enroll(ada);
        section.enroll(alan);
        assertNotNull(adasEnrollment, "enroll should return the new Enrollment");

        section.drop(ada);

        assertEquals(1, section.seatsRemaining(), "the dropped seat is free again");
        assertEquals(EnrollmentStatus.DROPPED, adasEnrollment.getStatus(),
                "the enrollment is kept, marked DROPPED -- not deleted");
        assertEquals(List.of(alan), section.roster());
    }

    @Test
    void dropPromotesTheFirstWaitlistedStudent() {
        Section section = sectionWithCapacity(1);
        Student ada = student("Ada Lovelace", "001");
        Student alan = student("Alan Turing", "002");
        Student grace = student("Grace Hopper", "003");
        section.enroll(ada);
        Enrollment alansEnrollment = section.enroll(alan);   // waitlist position 1
        Enrollment gracesEnrollment = section.enroll(grace); // waitlist position 2
        assertNotNull(alansEnrollment, "enroll should return the new Enrollment");

        section.drop(ada);

        assertEquals(EnrollmentStatus.ENROLLED, alansEnrollment.getStatus(),
                "first come, first served: Alan was waitlisted first");
        assertEquals(EnrollmentStatus.WAITLISTED, gracesEnrollment.getStatus(),
                "Grace keeps waiting");
        assertEquals(0, section.seatsRemaining(), "the freed seat went straight to Alan");
        assertEquals(List.of(alan), section.roster());
    }

    @Test
    void droppingAWaitlistedStudentPromotesNobody() {
        Section section = sectionWithCapacity(1);
        Student ada = student("Ada Lovelace", "001");
        Student alan = student("Alan Turing", "002");
        Student grace = student("Grace Hopper", "003");
        section.enroll(ada);
        section.enroll(alan);                                // waitlist position 1
        Enrollment gracesEnrollment = section.enroll(grace); // waitlist position 2
        assertNotNull(gracesEnrollment, "enroll should return the new Enrollment");

        section.drop(alan); // no seat opened -- Alan held none

        assertEquals(EnrollmentStatus.WAITLISTED, gracesEnrollment.getStatus());
        assertEquals(List.of(ada), section.roster());
        assertEquals(0, section.seatsRemaining());
    }

    @Test
    void dropStillWorksAfterAStudentReEnrolls() {
        Course c = new Course("ITP 220", "Java Programming II", 4);
        Section s = c.addSection("40001", 1, new OnlineAsync(), List.of());
        Student ada = new Student("s001", "Ada Lovelace", "ada@email.vccs.edu");
    
        s.enroll(ada);
        s.drop(ada);
        s.enroll(ada);   // a second Enrollment; the DROPPED one is still first in the list
        assertEquals(List.of(ada), s.roster());
    
        assertDoesNotThrow(() -> s.drop(ada));
        assertTrue(s.roster().isEmpty());
    }

    @Test
    void droppingSomeoneNeverEnrolledThrows() {
        Section section = sectionWithCapacity(2);
        Student stranger = student("Charles Babbage", "099");
        try {
            section.drop(stranger);
            fail("dropping a student with no active enrollment should throw IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // correct
        }
    }

    @Test
    void instructorIsStaffUntilAssignedThenBothEndsAgree() {
        Section section = sectionWithCapacity(2);
        assertEquals("Staff", section.instructorName(), "no instructor assigned yet");

        Instructor grace = new Instructor("i001", "Grace Hopper", "ghopper@virginiawestern.edu");
        section.assignInstructor(grace);

        assertEquals("Grace Hopper", section.instructorName());
        assertTrue(grace.sections().contains(section), "the Instructor end should know about the link");
        assertEquals(1, grace.teachingLoad());
    }

    @Test
    void conflictsAreDecidedByMeetingTimes() {
        Course course = new Course("ITP 220", "Java Programming II", 4);
        MeetingTime mwfMorning = new MeetingTime(
                Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
                LocalTime.of(10, 0), LocalTime.of(10, 50));
        MeetingTime mwLate = new MeetingTime(
                Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                LocalTime.of(10, 30), LocalTime.of(11, 20));
        MeetingTime trMorning = new MeetingTime(
                Set.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
                LocalTime.of(10, 0), LocalTime.of(10, 50));

        Section a = new Section(course, "40001", 25, new InPerson("Fralin Center", "262"), List.of(mwfMorning));
        Section b = new Section(course, "40002", 25, new InPerson("Fralin Center", "108"), List.of(mwLate));
        Section c = new Section(course, "40003", 25, new InPerson("Fralin Center", "262"), List.of(trMorning));
        Section async = new Section(course, "40004", 25, new OnlineAsync(), new ArrayList<MeetingTime>());

        assertTrue(a.conflictsWith(b), "MWF 10:00-10:50 collides with MW 10:30-11:20");
        assertFalse(a.conflictsWith(c), "different days, no conflict");
        assertFalse(a.conflictsWith(async), "an async section can never conflict");
    }

    @Test
    void aScheduleListsOnlyEnrolledSections() {
        Course course = new Course("ITP 220", "Java Programming II", 4);
        Section open = new Section(course, "40001", 2, new OnlineAsync(), new ArrayList<MeetingTime>());
        Section full = new Section(course, "40002", 1, new OnlineAsync(), new ArrayList<MeetingTime>());
        Student ada = student("Ada Lovelace", "001");
        full.enroll(student("Alan Turing", "002")); // takes the only seat

        open.enroll(ada); // Ada gets a seat here
        full.enroll(ada); // ...but is only waitlisted here

        assertEquals(List.of(open), ada.schedule(),
                "the schedule holds sections with a seat -- waitlists do not count");

        open.drop(ada);
        assertEquals(List.of(), ada.schedule(), "dropped sections leave the schedule");
    }
}
