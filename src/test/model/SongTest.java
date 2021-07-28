package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SongTest {
    private Song testSong;
    private Song s1;
    private Song s2;
    private Song s3;
    private Song s4;

    @BeforeEach
    void setUp() {
        s1 = new Song("song1", "artist1", 1.0);
        s2 = new Song("song2", "artist2", 2.0);
        s3 = new Song("song3", "artist3", 3.0);
    }

    @Test
    void testConstructor() {
        assertEquals("song1", s1.getTitle());
        assertEquals("artist2", s2.getArtist());
        assertEquals(3.0, s3.getDuration());
    }

    @Test
    void testToString() {
        String expected = "Song{title='song1', artist='artist1', duration=1.0}";
        assertEquals(expected, s1.toString());
    }
}
