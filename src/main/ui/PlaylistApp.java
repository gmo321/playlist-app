package ui;

import model.Song;
import model.Playlist;

import java.util.Scanner;

// UI Functionality and methods are implemented from Teller App. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Playlist application
public class PlaylistApp {
    private Playlist playlist;
    private Song song;
    private Scanner input;


    //EFFECTS: Runs the playlist application
    public PlaylistApp() {
        runPlaylist();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPlaylist() {
        boolean keepGoing = true;
        String command = null;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            addSongToPlaylist();
        } else if (command.equals("remove")) {
            removeSongFromPlaylist();
        } else if (command.equals("show")) {
            printPlaylist();
        } else if (command.equals("next")) {
            playNextSong();
        } else if (command.equals("prev")) {
            playPrevSong();
        } else if (command.equals("replay")) {
            replaySong();
        } else {
            System.out.println("Please try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes Playlist
    private void initialize() {
        playlist = new Playlist();
        input = new Scanner(System.in);
    }

    // EFFECTS: Displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> add song");
        System.out.println("\tremove -> remove song");
        System.out.println("\tnext -> play next song");
        System.out.println("\tprev -> play previous song");
        System.out.println("\treplay -> replay current song");
        System.out.println("\tshow -> show all songs");
    }

    // MODIFIES: this
    // EFFECTS: Adds the song to the playlist
    private void addSongToPlaylist() {
        System.out.println("Enter song name to add: ");
        String title = input.next();
        System.out.println("Enter the artist of the song: "
                + "");
        String artist = input.next();

        Song newSong = new Song();
        if (playlist.addSong(newSong)) {
            System.out.println(newSong.getTitle() + " was added to the playlist.");
        } else {
            System.out.println(newSong.getTitle() + " is already in playlist...");
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes song from the playlist
    private void removeSongFromPlaylist() {
        System.out.println("Enter song name to remove: ");
        String title = input.next();
        System.out.println("Enter the artist of the song: "
                + "");

        Song newSong = new Song();
        if (playlist.removeSong(newSong)) {
            System.out.println(newSong.getTitle() + " was removed from the playlist.");
        } else {
            System.out.println("Song is not in the playlist yet...");
        }
    }


    // EFFECTS: Given the current song, plays the next song on playlist
    private void playNextSong() {
        if (playlist.hasNextSong(song)) {
            System.out.println("Now playing " + playlist.playNext(song).toString());
        } else {
            System.out.println("The playlist has ended. Please add more songs.");
        }
    }

    // EFFECTS: Given the current song, plays the previous song on playlist
    private void playPrevSong() {
        if (playlist.hasPrevSong(song)) {
            System.out.println("Now playing " + playlist.playPrev(song).toString());
        } else {
            System.out.println("You cannot play the previous song.");
        }
    }

    // EFFECTS: Replays the current song on playlist
    private void replaySong() {
        if (playlist.isAlreadyInPlaylist(song)) {
            System.out.println("Now playing " + playlist.replay(song).toString());
        } else {
            System.out.println("This song is not in the playlist yet...");
        }
    }

    // EFFECTS: Prints out a list of all songs on the playlist
    private void printPlaylist() {
        System.out.println("Playlist: ");
        if (!playlist.isEmpty()) {
            System.out.println(playlist.toString());
        } else {
            System.out.println("Playlist is empty!");
        }
    }
}



