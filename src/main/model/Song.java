package model;

import java.util.Objects;

// Represents a song which has a name, artist, and duration
public class Song {
    private String title;              // a song title
    private String artist;             // the artist of the song
    private double duration;           // the duration of the song


    // EFFECTS: Song is title of the song, artist of the song and duration of the song
    public Song(String title, String artist, double duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public Song (String title) {
        this.title = title;
        this.artist = "";
    }

    public Song (String artist) {
        this.title = "";
        this.artist = artist;
    }


    // EFFECTS: returns title of song
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns artist of song
    public String getArtist() {
        return artist;
    }

    // EFFECTS: returns duration of song
    public double getDuration() {
        return duration;
    }

    //EFFECTS: returns a string representation of song title, artist, and duration
    @Override
    public String toString() {
        return "Song{"
                +
                "title='" + title + '\''
                +
                ", artist='" + artist + '\''
                +
                ", duration=" + duration
                +
                '}';
    }
}


