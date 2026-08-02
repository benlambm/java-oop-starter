package edu.vwcc.itp220mod2;

/**
 * A section that meets in a physical room.
 *
 * This is a record (introduced at the top of MeetingTime.java) that ALSO
 * implements an interface (introduced in Modality.java). That combination
 * is normal Java: the record supplies the data, the interface names the
 * behavior it promises.
 *
 * Instructions:
 * Implement both methods.
 *   describe()  must return exactly:  "In person at CAMPUS, room ROOM"
 *               e.g. new InPerson("Fralin Center", "262").describe()
 *               returns "In person at Fralin Center, room 262"
 *   isOnline()  a room is not the internet.
 */
public record InPerson(String campus, String room) implements Modality {

    @Override
    public String describe() {
        // TODO: your code goes here
        return "";
    }

    @Override
    public boolean isOnline() {
        // TODO: your code goes here
        return false;
    }
}
