package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

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
        s1 = new Song("song1", "artist1");
        s2 = new Song("song2", "artist2");
        s3 = new Song("song3", "artist3");
        s4 = new Song("song4", "artist4");
    }


    @Test
    void testConstructor() {
    }

    @Test
    void testAddSong() {
        assertTrue(testPlaylist.addSong(s1));
        assertTrue(testPlaylist.addSong(s2));
        testPlaylist.addSong(s1);
        assertFalse(testPlaylist.addSong(s1));
        assertTrue(testPlaylist.isAlreadyInPlaylist(s1));
        assertTrue(testPlaylist.isAlreadyInPlaylist(s2));
        assertTrue(testPlaylist.addSong(s3));
    }

    @Test
    void testRemoveSong() {
        assertFalse(testPlaylist.removeSong(s1));
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        assertTrue(testPlaylist.removeSong(s2));
        assertFalse(testPlaylist.isAlreadyInPlaylist(s2));
        assertTrue(testPlaylist.isAlreadyInPlaylist(s1));
    }

    @Test
    void testPlayNext() {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        testPlaylist.addSong(s3);
        assertEquals(s2, testPlaylist.playNext());
        assertEquals(s3, testPlaylist.playNext());
    }

    @Test
    void testPlayPrev() {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        testPlaylist.addSong(s3);
        testPlaylist.playNext();
        testPlaylist.playNext();
        assertEquals(s2, testPlaylist.playPrev());
        assertEquals(s1, testPlaylist.playPrev());
    }

    @Test
    void testReplay() {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s3);
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

    @Test
    void testHasNextSong() {
        assertFalse(testPlaylist.hasNextSong(s1));
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        assertTrue(testPlaylist.hasNextSong(s1));
        assertFalse(testPlaylist.hasNextSong(s2));
        assertFalse(testPlaylist.hasNextSong(s3));
    }

    @Test
    void testHasPrevSong() {
        assertFalse(testPlaylist.hasPrevSong(s2));
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        assertTrue(testPlaylist.hasPrevSong(s2));
        assertFalse(testPlaylist.hasPrevSong(s1));
        assertFalse(testPlaylist.hasPrevSong(s4));
    }

    @Test
    void testPrintCurrentSong() {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        assertEquals(s1, testPlaylist.getCurrentSong());
        testPlaylist.addSong(s2);
        assertEquals(s1, testPlaylist.getCurrentSong());
    }

//    //TODO
//    @Test
//    void testGetSongs() {
//      //  assertEquals("[]", testPlaylist.getSongs());
//        testPlaylist.addSong(s1);
//        testPlaylist.addSong(s2);
//        assertEquals(Collections.unmodifiableList(), testPlaylist.getSongs());
//    }

    @Test
    void testNumSongs() {
        assertEquals(0, testPlaylist.numSongs());
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        assertEquals(2, testPlaylist.numSongs());
    }

    @Test
    void testToJson() {

    }
}