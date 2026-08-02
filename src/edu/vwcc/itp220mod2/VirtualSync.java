package edu.vwcc.itp220mod2;

/**
 * A section that meets live over Zoom at scheduled times.
 * ("Sync" is short for synchronous -- everyone attends at once.)
 *
 * Instructions:
 * Implement both methods.
 *   describe()  must return exactly:  "Live on Zoom: URL"
 *               e.g. new VirtualSync("https://vccs.zoom.us/j/220").describe()
 *               returns "Live on Zoom: https://vccs.zoom.us/j/220"
 *   isOnline()  yes.
 */
public record VirtualSync(String meetingUrl) implements Modality {

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
