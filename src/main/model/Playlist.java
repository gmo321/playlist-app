package model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

//Represents a Playlist which user can add, remove, display songs
public class Playlist implements Iterable<Song> {
    private ArrayList<Song> playlist;
    private ListIterator<Song> iterator;


    public Playlist() {
        playlist = new ArrayList<Song>();
        iterator = playlist.listIterator();
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
    // EFFECTS: Removes song from playlist
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
