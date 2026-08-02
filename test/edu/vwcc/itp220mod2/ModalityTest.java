package edu.vwcc.itp220mod2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Instructions: Use these tests to check the three Modality records.
 *
 * Notice the variables are declared as Modality, the interface type, not
 * as the record types. The tests do not care which implementation they
 * hold -- that is the point of the interface.
 */
class ModalityTest {

    @Test
    void inPersonDescribesCampusAndRoom() {
        Modality m = new InPerson("Fralin Center", "262");
        assertEquals("In person at Fralin Center, room 262", m.describe());
    }

    @Test
    void virtualSyncDescribesTheZoomUrl() {
        Modality m = new VirtualSync("https://vccs.zoom.us/j/220");
        assertEquals("Live on Zoom: https://vccs.zoom.us/j/220", m.describe());
    }

    @Test
    void onlineAsyncDescribesItself() {
        Modality m = new OnlineAsync();
        assertEquals("Online, no scheduled meetings", m.describe());
    }

    @Test
    void onlyTheRoomBasedModalityIsOffline() {
        Modality room = new InPerson("Fralin Center", "262");
        Modality zoom = new VirtualSync("https://vccs.zoom.us/j/220");
        Modality async = new OnlineAsync();
        assertFalse(room.isOnline());
        assertTrue(zoom.isOnline());
        assertTrue(async.isOnline());
    }
}
