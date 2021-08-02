package persistence;

import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
// Tests are implemented with reference to JsonSerializationDemo, link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Playlist pl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlaylist.json");
        try {
            Playlist pl = reader.read();
            assertEquals(0, pl.numSongs());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlaylist.json");
        try {
            Playlist pl = reader.read();
            List<Song> playlist = pl.getSongs();
            assertEquals(3, playlist.size());
            checkSong("song1", "artist1", playlist.get(0));
            checkSong("song2", "artist2", playlist.get(1));
            checkSong("song3", "artist3", playlist.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
