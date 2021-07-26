package model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

// Methods are implemented with reference to Lab 5, link below:
// https://github.students.cs.ubc.ca/CPSC210-2021S-T2/lab5_h5h3b
// Methods are implemented with reference to IntergerSetIntersect starter, link below:
// https://github.students.cs.ubc.ca/CPSC210/IntegerSetLecLab
// Represents a Playlist which user can add, remove, display songs
public class Playlist implements Iterable<Song> {
    private ArrayList<Song> playlist;
    private ListIterator<Song> iterator;

    // EFFECTS: Arraylist called playlist with elements of object Song, uses listIterator interface
    public Playlist() {
        playlist = new ArrayList<Song>();
        iterator = playlist.listIterator();
    }

    // MODIFIES: this
    // EFFECTS: If song is not already in playlist, add song to end of playlist and return true, false otherwise
    public void addSong(Song song) {
            playlist.add(song);
    }

    // MODIFIES: this
    // EFFECTS: If song is already in playlist, remove song from playlist and return true, false otherwise
    public void removeSong(Song song) {
            playlist.remove(song);
    }

    // EFFECTS: Plays next song on playlist
    public Song playNext() {
        return iterator.next();
    }

    // EFFECTS: Plays previous song on playlist
    public Song playPrev() {
        return iterator.previous();
    }

    // EFFECTS: Replays current song on playlist
    public Song replay() {
        return iterator.previous();

    }

    // EFFECTS: Returns true if song title matches another song title already in playlist, false otherwise
    public boolean isAlreadyInPlaylist(Song s) {
        for (Song song: playlist) {
            return song.getTitle().equals(s.getTitle());
        }
        return false;
    }

    // EFFECTS: Returns true if playlist is empty, false otherwise
    public boolean isEmpty() {
        return playlist.size() == 0;
    }


    @Override
    public ListIterator<Song> iterator() {
        return playlist.listIterator();
    }
}
