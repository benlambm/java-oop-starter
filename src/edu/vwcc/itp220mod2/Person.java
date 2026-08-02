package edu.vwcc.itp220mod2;

/**
 * NEW CONCEPT -- abstract class.
 *
 * Most of this module is HAS-A: a Course has Sections, a Section has
 * MeetingTimes. Person is the one IS-A in the model: a Student is a
 * Person, an Instructor is a Person. When two classes truly share both
 * data (id, name, email) and behavior (contactLine), a superclass keeps
 * that shared part in one place.
 *
 * "abstract" means two things here:
 *
 *   1. You cannot write "new Person(...)". Nobody at the registrar is a
 *      plain Person -- they are always a Student or an Instructor. The
 *      compiler now enforces that.
 *
 *   2. An abstract METHOD (see role() below) has no body. It is a promise
 *      that every concrete subclass must fill in -- the same idea as an
 *      interface method, but living inside a class that also carries
 *      fields and real code.
 *
 * The payoff is polymorphism: contactLine() below is written ONCE, here,
 * yet prints "(Student)" or "(Instructor)" depending on which subclass the
 * object really is, because its call to role() lands in the subclass.
 *
 * Instructions:
 * Implement contactLine(). role() stays abstract -- its bodies belong in
 * Student.java and Instructor.java.
 */
public abstract class Person {

    // private final: set once in the constructor, then read-only forever.
    // Subclasses reach them through the getters like everyone else.
    private final String id;
    private final String name;
    private final String email;

    /**
     * protected: only subclasses call this, via super(...), as the first
     * line of their own constructors.
     */
    protected Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    /**
     * One word naming what kind of person this is.
     * No body here -- each subclass must supply its own.
     */
    public abstract String role();

    /**
     * One line suitable for a class roster printout, in exactly this
     * format:
     *
     *     NAME <EMAIL> (ROLE)
     *
     * e.g.  "Ada Lovelace <ada@email.vccs.edu> (Student)"
     *
     * Build it from getName(), getEmail(), and role(). Calling role() from
     * here is the whole trick: this superclass method produces different
     * output per subclass without an if statement in sight.
     *
     * @return the formatted line
     */
    public String contactLine() {
        // TODO: your code goes here
        return "";
    }
}
