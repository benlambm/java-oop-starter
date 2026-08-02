package edu.vwcc.itp220mod2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A catalog entry: "ITP 220, Java Programming II, 4 credits."
 *
 * A Course is what CAN be offered. A Section is one actual offering --
 * CRN, seats, meeting times. Keep the two ideas apart and "same course,
 * three sections this fall" falls out naturally.
 *
 * Course-to-Section is COMPOSITION, the strong form of has-a: a Section
 * cannot exist without its Course, so Sections are created INSIDE this
 * class -- look at addSection(). The Section constructor is deliberately
 * package-private, so outside code cannot build an orphan Section; the
 * compiler itself enforces the ownership.
 *
 * Instructions:
 * Implement addSection(), sections(), and findSection().
 */
public class Course {

    private final String code;
    private final String title;
    private final int credits;

    // The owned Sections, in the order they were added.
    private final List<Section> sections = new ArrayList<>();

    public Course(String code, String title, int credits) {
        if (credits <= 0) {
            throw new IllegalArgumentException("credits must be positive");
        }
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    /**
     * Create a new Section of this course, remember it, and return it.
     *
     * Three steps:
     *   1. new Section(this, crn, capacity, modality, meetingTimes)
     *      -- passing "this" is the composition: the newborn Section knows
     *      which Course owns it from its first breath.
     *   2. add it to the sections list.
     *   3. return it.
     *
     * @param crn          the registration number students search for
     * @param capacity     how many seats
     * @param modality     how it meets (InPerson, VirtualSync, OnlineAsync)
     * @param meetingTimes weekly patterns; may be empty for async sections
     * @return the newly created Section
     */
    public Section addSection(String crn, int capacity, Modality modality,
            List<MeetingTime> meetingTimes) {
        // TODO: your code goes here
        return null;
    }

    /**
     * All sections of this course, as a READ-ONLY view.
     *
     * Return Collections.unmodifiableList(sections) -- the import is
     * already at the top of the file. Returning the real list would let
     * any caller do course.sections().add(...) and sneak a Section past
     * addSection(). The test checks that writing through the view throws.
     *
     * @return an unmodifiable view of the sections list
     */
    public List<Section> sections() {
        // TODO: your code goes here
        return new ArrayList<>();
    }

    /**
     * Find the section with the given CRN. Loop and compare with
     * .equals() -- CRNs are Strings, and == on Strings is a bug you only
     * catch at 11 pm the night before the demo.
     *
     * @param crn the CRN to look for
     * @return the matching Section, or null when no section has that CRN
     */
    public Section findSection(String crn) {
        // TODO: your code goes here
        return null;
    }
}
