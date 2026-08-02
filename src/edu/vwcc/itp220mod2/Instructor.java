package edu.vwcc.itp220mod2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An instructor: IS-A Person, and is ASSOCIATED with the Sections they
 * teach. Plain association this time -- no Enrollment-style middle object,
 * because the link itself has nothing extra to remember.
 *
 * Instructions:
 * Implement role() and teachingLoad().
 */
public class Instructor extends Person {

    // This end of the Section <-> Instructor association.
    // Section.assignInstructor() keeps it in sync -- see addSection.
    private final List<Section> sections = new ArrayList<>();

    public Instructor(String id, String name, String email) {
        super(id, name, email);
    }

    /**
     * @return exactly "Instructor"
     */
    @Override
    public String role() {
        // TODO: your code goes here
        return "";
    }

    /**
     * How many sections this instructor is currently assigned.
     *
     * @return the count
     */
    public int teachingLoad() {
        // TODO: your code goes here
        return 0;
    }

    /**
     * A read-only view of the sections taught. Already done for you.
     */
    public List<Section> sections() {
        return Collections.unmodifiableList(sections);
    }

    /**
     * Package-private, same idea as Student.addEnrollment: only
     * Section.assignInstructor() calls this, to keep both ends of the
     * association in sync. Already done for you.
     */
    void addSection(Section s) {
        sections.add(s);
    }
}
