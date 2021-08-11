package persistence;

import exceptions.SongAlreadyInPlaylistException;
import model.Playlist;
import model.Song;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Methods are implemented with reference to JsonSerializationDemo, link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


// Represents a reader that reads playlist from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: Constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: Reads playlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Playlist read() throws IOException, SongAlreadyInPlaylistException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlaylist(jsonObject);
    }

    // EFFECTS: Reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }


    // EFFECTS: Parses playlist from JSON object and returns it
    private Playlist parsePlaylist(JSONObject jsonObject) throws SongAlreadyInPlaylistException {
        Playlist pl = new Playlist();
        addSongs(pl, jsonObject);
        return pl;
    }

    // MODIFIES: pl
    // EFFECTS: Parses songs from JSON object and adds them to playlist
    private void addSongs(Playlist pl, JSONObject jsonObject) throws SongAlreadyInPlaylistException {
        JSONArray jsonArray = jsonObject.getJSONArray("playlist");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(pl, nextSong);
        }
    }

    // MODIFIES: pl
    // EFFECTS: Parses song from JSON object and adds it to playlist
    private void addSong(Playlist pl, JSONObject jsonObject) throws SongAlreadyInPlaylistException {
        String title = jsonObject.getString("title");
        String artist = jsonObject.getString("artist");
        Song song = new Song(title, artist);
        pl.addSong(song);
    }
}

