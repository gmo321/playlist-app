package persistence;

import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSong(String title, String artist, Song song) {
        assertEquals(title, song.getTitle());
        assertEquals(artist, song.getArtist());
    }
}
