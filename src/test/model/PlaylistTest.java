package model;

import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    void testAddSongNoException() {
        try {
            testPlaylist.addSong(s2);
        } catch (SongAlreadyInPlaylistException e) {
            fail("Unexpected SongAlreadyInPlaylistException");
        }
    }

    @Test
    void testAddSongAlreadyInPlaylistException() {
        try {
            testPlaylist.addSong(s1);
            assertFalse(testPlaylist.addSong(s1));
            fail("SongAlreadyInPlaylistException was not thrown!");
        } catch (SongAlreadyInPlaylistException e) {
            //pass
        }
    }

    @Test
    void testRemoveSongNoException() throws SongAlreadyInPlaylistException {
        testPlaylist.addSong(s1);
        try {
            testPlaylist.removeSong(s1);
        } catch (SongNotInPlaylistException e) {
            fail("Unexpected SongNotInPlaylistException");
        }
    }

    @Test
    void testRemoveSongException() throws SongAlreadyInPlaylistException {
        assertTrue(testPlaylist.isEmpty());
        try {
            assertFalse(testPlaylist.removeSong(s1));
            testPlaylist.addSong(s2);
            assertTrue(testPlaylist.removeSong(s2));
            testPlaylist.addSong(s3);
            assertFalse(testPlaylist.removeSong(s2));
            assertFalse(testPlaylist.removeSong(new Song("song4", "artist4")));
            fail("SongNotInPlaylistException was not thrown!");
        } catch (SongNotInPlaylistException e) {
            // pass
        }
    }

    @Test
    void testPlayNextNoException() throws SongAlreadyInPlaylistException {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        try {
            assertEquals(s2, testPlaylist.playNext());
        } catch (NoNextSongException e) {
            fail("Unexpected NoNextSongException");
        } catch (NoSongInPlaylistException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testPlayNextException() throws SongAlreadyInPlaylistException {
        testPlaylist.addSong(s1);
        try {
            testPlaylist.playNext();
            fail("NoNextSongException was not thrown!");
        } catch (NoNextSongException e) {
            // pass
        } catch (NoSongInPlaylistException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testPlayPrevNoException() throws SongAlreadyInPlaylistException, NoNextSongException,
            NoSongInPlaylistException {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        testPlaylist.playNext();
        try {
            testPlaylist.playPrev();
        } catch (NoPreviousSongException e) {
            fail("Unexpected NoPrevSongException");
        }
    }

    @Test
    void testPlayPrevException() throws SongAlreadyInPlaylistException, NoSongInPlaylistException {
        testPlaylist.addSong(s1);
        try {
            testPlaylist.playPrev();
            fail("NoPrevSongException was not thrown!");
        } catch (NoPreviousSongException e) {
            // pass
        }
    }

    @Test
    void testReplayNoException() throws SongAlreadyInPlaylistException {
        testPlaylist.addSong(s1);
        try {
            testPlaylist.replay();
        } catch (NoSongInPlaylistException e) {
            fail("Unexpected NoSongInPlaylistException");
        }
    }

    @Test
    void testReplayException() {
        try {
            testPlaylist.replay();
            fail("NoSongInPlaylistException was not thrown!");
        } catch (NoSongInPlaylistException e) {
            // pass
        }
    }

    @Test
    void testHasNextSong() throws SongAlreadyInPlaylistException {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        assertTrue(testPlaylist.hasNextSong(s1));
        assertFalse(testPlaylist.hasNextSong(s2));
        assertFalse(testPlaylist.hasNextSong(s3));
    }


    @Test
    void testHasPrevSong() throws SongAlreadyInPlaylistException {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        assertTrue(testPlaylist.hasPrevSong(s2));
        assertFalse(testPlaylist.hasPrevSong(s1));
        assertFalse(testPlaylist.hasPrevSong(s3));
    }

    @Test
    void testGetCurrentSongNoException() throws SongAlreadyInPlaylistException {
        testPlaylist.addSong(s1);
        try {
            testPlaylist.getCurrentSong();
        } catch (NoSongInPlaylistException e) {
            fail("Unexpected NoSongInPlaylistException");
        }
    }

    @Test
    void testGetCurrentSongException() {
        try {
            testPlaylist.getCurrentSong();
            fail("NoSongInPlaylistException was not thrown!");
        } catch (NoSongInPlaylistException e) {
            // pass
        }
    }

    @Test
    void testIsAlreadyInPlaylist() throws SongAlreadyInPlaylistException {
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        testPlaylist.addSong(s3);
        assertTrue(testPlaylist.isAlreadyInPlaylist(s1));
        assertTrue(testPlaylist.isAlreadyInPlaylist(s2));
        assertTrue(testPlaylist.isAlreadyInPlaylist(s3));
        assertFalse(testPlaylist.isAlreadyInPlaylist(s4));
        assertTrue(testPlaylist.isAlreadyInPlaylist(new Song("song1", "artist1")));
        assertFalse(testPlaylist.isAlreadyInPlaylist(new Song("song2", "artist3")));
        assertFalse(testPlaylist.isAlreadyInPlaylist(new Song("song5", "artist2")));
        assertFalse(testPlaylist.isAlreadyInPlaylist(new Song("song1", "artist5")));
        assertFalse(testPlaylist.isAlreadyInPlaylist(new Song("song5", "artist5")));
    }

    @Test
    void testIsEmpty() throws SongAlreadyInPlaylistException {
        assertTrue(testPlaylist.isEmpty());
        testPlaylist.addSong(s1);
        assertFalse(testPlaylist.isEmpty());
    }

    @Test
    void testGetSongs() throws SongAlreadyInPlaylistException {
        assertEquals(Arrays.asList(), testPlaylist.getSongs());
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        testPlaylist.addSong(s3);
        assertEquals(Arrays.asList(s1, s2, s3), testPlaylist.getSongs());
    }

    @Test
    void testNumSongs() throws SongAlreadyInPlaylistException {
        assertEquals(0, testPlaylist.numSongs());
        testPlaylist.addSong(s1);
        testPlaylist.addSong(s2);
        assertEquals(2, testPlaylist.numSongs());
    }

}