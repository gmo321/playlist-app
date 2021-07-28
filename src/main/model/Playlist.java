package model;


import java.sql.SQLOutput;
import java.util.ArrayList;

// Methods are implemented with reference to Lab 5, link below:
// https://github.students.cs.ubc.ca/CPSC210-2021S-T2/lab5_h5h3b
// Methods are implemented with reference to IntegerSetIntersect starter, link below:
// https://github.students.cs.ubc.ca/CPSC210/IntegerSetLecLab
// Represents a Playlist which user can add, remove, play next song, play previous song, replay current song,
// and display all songs

public class Playlist {
    private ArrayList<Song> playlist;


    //EFFECTS: Playlist is an arraylist with elements of object Song
    public Playlist() {
        playlist = new ArrayList<Song>();
    }

    // MODIFIES: this
    // EFFECTS: If song is not already in playlist, add song to end of playlist and return true,
    // false otherwise
    public boolean addSong(Song song) {
        if (!isAlreadyInPlaylist(song)) {
            playlist.add(song);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: If song is already in playlist, remove song from playlist and return true, false otherwise
    public boolean removeSong(Song song) {
        if (isAlreadyInPlaylist(song)) {
            playlist.remove(song);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: Plays next song on playlist
    public Song playNext(Song song) {
        for (int i = 0; i < playlist.size(); i++) {
            if (hasNextSong(song)) {
                return playlist.get(playlist.indexOf(song) + 1);
            } else {
                System.out.println("There is no next song on the playlist.");
            }
        }
    }


    // EFFECTS: Plays previous song on playlist
    public Song playPrev(Song song) {
        for (int i = 0; i < playlist.size(); i++) {
            if (hasPrevSong(song)) {
                return playlist.get(playlist.indexOf(song) - 1);
            }
        }
        return null;
    }

    // EFFECTS: Replays current song on playlist
    public Song replay(Song song) {
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.contains(song)) {
                return playlist.get(playlist.indexOf(song));
            }
        }
        return null;
    }

    // EFFECTS: Returns true if song title matches another song title already in playlist, false otherwise
    public boolean isAlreadyInPlaylist(Song s) {
        for (Song song : playlist) {
            if (song.getTitle().equals(s.getTitle())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns true if playlist is empty, false otherwise
    public boolean isEmpty() {
        return playlist.isEmpty();
    }

    // EFFECTS: Returns true if the current song playing has a song after it in the playlist, false otherwise
    public boolean hasNextSong(Song song) {
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.contains(song)) {
                return playlist.indexOf(song) + 1 < playlist.size();
            } else {
                return false;
            }
        }
        return false;
    }

    // EFFECTS: Returns true if the current song playing has a song before it in the playlist, false otherwise
    public boolean hasPrevSong(Song song) {
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.contains(song)) {
                return playlist.indexOf(song) - 1 >= 0;
            } else {
                return false;
            }
        }
        return false;
    }
}

