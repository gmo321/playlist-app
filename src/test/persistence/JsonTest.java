package persistence;

import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;
// Tests are implemented with reference to JsonSerializationDemo, link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTest {
    protected void checkSong(String title, String artist, Song song) {
        assertEquals(title, song.getTitle());
        assertEquals(artist, song.getArtist());
    }
}
