package edu.vwcc.itp220mod2;

/**
 * NEW CONCEPT -- interface.
 *
 * An interface is a contract: a list of methods with no bodies. Any type
 * that declares "implements Modality" promises to provide real bodies for
 * every method listed here. The compiler holds it to that promise.
 *
 * Why bother? Because Section can then hold a Modality without knowing or
 * caring WHICH kind it has:
 *
 *     Modality m = section.getModality();   // could be any of the three
 *     System.out.println(m.describe());     // the right version runs
 *
 * That last line is polymorphism: one method call, three possible
 * behaviors, chosen at runtime by the actual object's type. Swapping in a
 * different implementation without touching the calling code is the idea
 * behind the Strategy design pattern -- you will meet it again.
 *
 * Three records implement this interface: InPerson, VirtualSync, and
 * OnlineAsync. (Records are introduced at the top of MeetingTime.java.)
 * Your TODOs are in those three files -- nothing to implement here.
 */
public interface Modality {

    /**
     * One human-readable line saying how this section meets.
     * Each implementing record's file states its exact expected wording.
     */
    String describe();

    /**
     * Does this modality happen over the internet rather than in a room?
     */
    boolean isOnline();
}
