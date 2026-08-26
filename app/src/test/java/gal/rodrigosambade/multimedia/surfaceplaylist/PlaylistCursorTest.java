package gal.rodrigosambade.multimedia.surfaceplaylist;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PlaylistCursorTest {
    @Test public void wrapsAtEnd() {
        PlaylistCursor cursor = new PlaylistCursor(2);
        assertEquals(0, cursor.current());
        assertEquals(1, cursor.next());
        assertEquals(0, cursor.next());
    }
}
