package edu.vwcc.itp220mod2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Instructions: Use these tests to check role() in Student and Instructor,
 * and contactLine() in Person.
 */
class PersonTest {

    @Test
    void aStudentKnowsItsRole() {
        Student ada = new Student("s001", "Ada Lovelace", "ada@email.vccs.edu");
        assertEquals("Student", ada.role());
    }

    @Test
    void anInstructorKnowsItsRole() {
        Instructor grace = new Instructor("i001", "Grace Hopper", "ghopper@virginiawestern.edu");
        assertEquals("Instructor", grace.role());
    }

    @Test
    void contactLineFormatsNameEmailAndRole() {
        Student ada = new Student("s001", "Ada Lovelace", "ada@email.vccs.edu");
        assertEquals("Ada Lovelace <ada@email.vccs.edu> (Student)", ada.contactLine());
    }

    @Test
    void oneContactLineMethodServesBothSubclasses() {
        // Both variables are typed Person. contactLine() lives in Person
        // and is written once, yet the output differs -- its call to
        // role() lands in whichever subclass the object really is.
        Person p1 = new Student("s001", "Ada Lovelace", "ada@email.vccs.edu");
        Person p2 = new Instructor("i001", "Grace Hopper", "ghopper@virginiawestern.edu");
        assertEquals("Ada Lovelace <ada@email.vccs.edu> (Student)", p1.contactLine());
        assertEquals("Grace Hopper <ghopper@virginiawestern.edu> (Instructor)", p2.contactLine());
    }
}
