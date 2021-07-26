package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
    private Playlist testPlaylist;
    private Song s1;
    private Song s2;
    private Song s3;
    private Song s4;

    @BeforeEach
    void setUp() {
        testPlaylist = new Playlist();
        s1 = new Song("song1", "artist1", 1.0);
        s2 = new Song("song2", "artist2", 2.0);
        s3 = new Song("song3", "artist3", 3.0);
    }


    @Test
    void testConstructor() {

    }

    @Test
    void testAddSong() {
        testPlaylist.addSong(s1);
        assertTrue(testPlaylist.isAlreadyInPlaylist(s1));
        testPlaylist.addSong(s2);
        assertTrue(testPlaylist.isAlreadyInPlaylist(s1));
        assertFalse(testPlaylist.isAlreadyInPlaylist(new Song("test", "test", 5)));
        assertFalse(testPlaylist.addSong(s1));
    }

    @Test
    void testRemoveSong() {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        testPlaylist.removeSong(s2);
        assertFalse(testPlaylist.isAlreadyInPlaylist(s2));
    }

    @Test
    void testPlayNext() {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        testPlaylist.addSong(s2);
        assertEquals(s1, testPlaylist.playNext());
        assertEquals(s2, testPlaylist.playNext());
    }

    @Test
    void testPlayPrev() {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        testPlaylist.addSong(s2);
        assertEquals(s1, testPlaylist.playPrev());
    }

    @Test
    void testReplay() {
        testPlaylist.addSong(s1);
        assertEquals(s1, testPlaylist.replay());
    }

    @Test
    void testIsAlreadyInPlaylist() {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        testPlaylist.addSong(s3);
        assertTrue(testPlaylist.isAlreadyInPlaylist(s1));
        assertTrue(testPlaylist.isAlreadyInPlaylist(s2));
        assertTrue(testPlaylist.isAlreadyInPlaylist(s3));
        assertFalse(testPlaylist.isAlreadyInPlaylist(s4));
    }

    @Test
    void testIsEmpty() {
        assertTrue(testPlaylist.isEmpty());
        testPlaylist.addSong(s1);
        assertFalse(testPlaylist.isEmpty());
    }

}