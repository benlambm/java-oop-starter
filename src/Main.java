import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import edu.vwcc.itp220mod2.*;

/**
 * A demo, not an exercise: one tiny registration day at a very small
 * college. There are NO tests for this file and nothing to implement in
 * it. Run it AFTER your tests are green to show all the pieces working
 * together.
 *
 * On a fresh clone it stops immediately, because the model under it is
 * still a pile of TODOs. That is the correct starting behavior.
 */
public class Main {

    public static void main(String[] args) {

        // --- The catalog: one Course, three Sections (composition) ------
        Course itp220 = new Course("ITP 220", "Java Programming II", 4);

        MeetingTime mwfMorning = new MeetingTime(
                Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
                LocalTime.of(10, 0), LocalTime.of(10, 50));
        MeetingTime mwLate = new MeetingTime(
                Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                LocalTime.of(10, 30), LocalTime.of(11, 20));
        MeetingTime tuesdayEvening = new MeetingTime(
                Set.of(DayOfWeek.TUESDAY),
                LocalTime.of(18, 0), LocalTime.of(19, 15));

        Section inPerson = itp220.addSection("40001", 2,
                new InPerson("Fralin Center", "262"), List.of(mwfMorning));

        if (inPerson == null) {
            System.out.println("Course.addSection() is not implemented yet.");
            System.out.println("Go make the tests green first -- then run me again.");
            return;
        }

        Section zoom = itp220.addSection("40002", 24,
                new VirtualSync("https://vccs.zoom.us/j/220"), List.of(tuesdayEvening));
        Section async = itp220.addSection("40003", 30,
                new OnlineAsync(), new ArrayList<MeetingTime>());
        Section conflicting = itp220.addSection("40004", 24,
                new InPerson("Fralin Center", "108"), List.of(mwLate));

        // --- Staffing (association, one end may be empty) ---------------
        Instructor grace = new Instructor("i001", "Grace Hopper", "ghopper@virginiawestern.edu");
        inPerson.assignInstructor(grace);
        zoom.assignInstructor(grace);
        // async stays unassigned on purpose -- watch it print "Staff".

        System.out.println("=== " + itp220.getCode() + ": " + itp220.getTitle()
                + " (" + itp220.getCredits() + " cr) ===");
        for (Section s : itp220.sections()) {
            // Polymorphism: one describe() call, three behaviors.
            System.out.println("  CRN " + s.getCrn()
                    + " | " + s.getModality().describe()
                    + " | " + s.instructorName()
                    + " | seats left: " + s.seatsRemaining());
        }
        System.out.println("Prof. Hopper's teaching load: " + grace.teachingLoad() + " sections");

        // --- Registration day (association via Enrollment) ---------------
        Student ada = new Student("s001", "Ada Lovelace", "ada@email.vccs.edu");
        Student alan = new Student("s002", "Alan Turing", "alan@email.vccs.edu");
        Student edsger = new Student("s003", "Edsger Dijkstra", "edsger@email.vccs.edu");

        System.out.println();
        System.out.println("=== Registration opens for CRN 40001 (capacity 2) ===");
        inPerson.enroll(ada);
        inPerson.enroll(alan);
        Enrollment edsgersTry = inPerson.enroll(edsger);
        System.out.println("  Edsger arrives third and is: " + edsgersTry.getStatus());

        try {
            inPerson.enroll(ada);
        } catch (IllegalArgumentException e) {
            System.out.println("  Registrar blocks a double-enroll: " + e.getMessage());
        }

        printRosterAndWaitlist(inPerson);

        System.out.println();
        System.out.println("=== Alan drops -- who gets the seat? ===");
        inPerson.drop(alan);
        printRosterAndWaitlist(inPerson);

        // --- Conflict checking (value objects earning their keep) --------
        System.out.println();
        System.out.println("=== Can Ada also take CRN 40004 (MW 10:30-11:20)? ===");
        System.out.println("  conflicts with her MWF 10:00 class: "
                + inPerson.conflictsWith(conflicting));
        System.out.println("  conflicts with the async section:   "
                + inPerson.conflictsWith(async));
        async.enroll(ada);

        // --- One loop, every kind of Person (inheritance) ----------------
        System.out.println();
        System.out.println("=== Contact sheet ===");
        List<Person> everyone = new ArrayList<>();
        everyone.add(ada);
        everyone.add(alan);
        everyone.add(edsger);
        everyone.add(grace);
        for (Person p : everyone) {
            System.out.println("  " + p.contactLine());
        }

        System.out.println();
        System.out.println("=== Ada's schedule ===");
        for (Section s : ada.schedule()) {
            System.out.println("  CRN " + s.getCrn() + " -- " + s.getModality().describe());
        }
    }

    /**
     * Small helper so the demo prints the same way at every checkpoint.
     * Note it asks the Section for roster() and waitlist() -- it does not
     * dig through enrollment lists itself. Tell, don't ask.
     */
    private static void printRosterAndWaitlist(Section s) {
        System.out.println("  Roster (seats " + s.seatsRemaining() + "/" + s.getCapacity() + " free):");
        for (Student st : s.roster()) {
            System.out.println("    " + st.getName());
        }
        System.out.println("  Waitlist:");
        for (Student st : s.waitlist()) {
            System.out.println("    " + st.getName());
        }
    }
}
