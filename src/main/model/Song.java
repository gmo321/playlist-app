package model;

import org.json.JSONObject;
import persistence.Writable;


// Represents a song which has a name, artist, and duration
public class Song implements Writable {
    private String title;              // a song title
    private String artist;             // the artist of the song


    // EFFECTS: Song is title of the song, artist of the song and duration of the song
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    // EFFECTS: Returns title of song
    public String getTitle() {
        return title;
    }

    // EFFECTS: Returns artist of song
    public String getArtist() {
        return artist;
    }


    //EFFECTS: Returns a string representation of song title, artist, and duration
    @Override
    public String toString() {
        return "Song{"
                +
                "title='" + title + '\''
                +
                ", artist='" + artist + '\''
                +
                '}';
    }

    // EFFECTS: Returns song as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("artist", artist);
        return json;
    }
}


