package persistence;

import exceptions.SongAlreadyInPlaylistException;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
// Tests are implemented with reference to JsonSerializationDemo, link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Playlist pl = new Playlist();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPlaylist() throws SongAlreadyInPlaylistException {
        try {
            Playlist pl = new Playlist();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlaylist.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlaylist.json");
            pl = reader.read();
            assertTrue(pl.isEmpty());
        } catch (IOException e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    void testWriterGeneralPlaylist() throws SongAlreadyInPlaylistException {
        try {
            Playlist pl = new Playlist();
            pl.addSong(new Song("song2", "artist2"));
            pl.addSong(new Song("song1", "artist1"));
            pl.addSong(new Song("song3", "artist3"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlaylist.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlaylist.json");
            pl = reader.read();
            List<Song> playlist = pl.getSongs();
            assertEquals(3, playlist.size());
            checkSong("song2", "artist2", playlist.get(0));
            checkSong("song1", "artist1", playlist.get(1));
            checkSong("song3", "artist3", playlist.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
