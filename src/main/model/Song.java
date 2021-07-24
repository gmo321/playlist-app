package model;

// Represents a song which has a name, artist, and duration
public class Song {
    private String title;           // a song title
    private String artist;         // the artist of the song
    private double duration;           // the duration of the song


    public Song(String title, String artist, double duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public double getDuration() {
        return duration;
    }

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


