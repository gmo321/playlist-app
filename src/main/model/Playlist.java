package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Methods are implemented with reference to Lab 5, link below:
// https://github.students.cs.ubc.ca/CPSC210-2021S-T2/lab5_h5h3b
// Methods are implemented with reference to IntegerSetIntersect starter, link below:
// https://github.students.cs.ubc.ca/CPSC210/IntegerSetLecLab
// Represents a Playlist which user can add, remove, play next song, play previous song, replay current song,
// and display all songs

public class Playlist implements Writable {
    private List<Song> playlist;
    private int currentSong;


    //EFFECTS: Playlist is an arraylist with elements of object Song
    public Playlist() {
        playlist = new ArrayList<>();
        currentSong = 0;
    }

    // MODIFIES: this
    // EFFECTS: If song is not already in playlist, add song to end of playlist and return true, false otherwise
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
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.get(i).getTitle().equals(song.getTitle())
                    && playlist.get(i).getArtist().equals(song.getArtist())) {
                playlist.remove(i);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Plays next song on playlist
    public Song playNext() {
        this.currentSong++;
        return playlist.get(currentSong);
    }

    // EFFECTS: Plays previous song on playlist
    public Song playPrev() {
        this.currentSong--;
        return playlist.get(currentSong);
    }

    // EFFECTS: Replays current song on playlist
    public Song replay() {
        return playlist.get(currentSong);
    }

    // EFFECTS: Returns true if song title matches another song title already in playlist, false otherwise
    public boolean isAlreadyInPlaylist(Song s) {
        for (Song song : playlist) {
            if (song.getTitle().equals(s.getTitle().toLowerCase())
                    && song.getArtist().equals(s.getArtist().toLowerCase())) {
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
        if (isAlreadyInPlaylist(song)) {
            return playlist.indexOf(song) + 1 < playlist.size();
        }
        return false;
    }

    // EFFECTS: Returns true if the current song playing has a song before it in the playlist, false otherwise
    public boolean hasPrevSong(Song song) {
        if (isAlreadyInPlaylist(song)) {
            return playlist.indexOf(song) - 1 >= 0;
        }
        return false;
    }

    // EFFECTS: Returns the current song of playlist starting at index 0
    public Song getCurrentSong() {
        //(currentSong < playlist.size() && currentSong > 0) {
        return playlist.get(currentSong);
    }


    // EFFECTS: returns an unmodifiable list of songs in the playlist
    public List<Song> getSongs() {
        return Collections.unmodifiableList(playlist);
    }

    public int numSongs() {
        return playlist.size();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("playlist", playlistToJson());
        return json;
    }

    // EFFECTS: returns songs in this playlist as a JSON array
    private JSONArray playlistToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : playlist) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}



