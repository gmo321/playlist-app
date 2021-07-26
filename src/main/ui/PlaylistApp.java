package ui;

import model.Song;
import model.Playlist;

import java.util.Iterator;
import java.util.Scanner;

// UI Functionality and methods are implemented from Teller App. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Playlist application
public class PlaylistApp {
    private Playlist playlist;
    private Song song;
    private Scanner input;
    Iterator<Song> iterator = playlist.iterator();


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
            addSong();
        } else if (command.equals("remove")) {
            removeSong();
        } else if (command.equals("show songs")) {
            printPlaylist();
        } else if (command.equals("next")) {
            playNext();
        } else if (command.equals("prev")) {
            playPrev();
        } else if (command.equals("replay")) {
            replay();
        } else {
            System.out.println("Please try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes Playlist
    private void initialize() {
        playlist = new Playlist();
        song = new Song("test song", "test artist", 1.0);
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> add song");
        System.out.println("\tremove -> remove song");
        System.out.println("\tshow songs-> show all songs");
        System.out.println("\tnext -> play next song");
        System.out.println("\tprev -> play previous song");
        System.out.println("\treplay -> replay current song");
    }

    // MODIFIES: this
    // EFFECTS: adds a new song to the playlist
    private void addSong() {
        System.out.println("Enter song name to add: ");
        String title = input.next();

        if (!playlist.isAlreadyInPlaylist(song)) {
            playlist.addSong(song);
        } else {
            System.out.println("Song is already in playlist...");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes song from the playlist
    private void removeSong() {
        System.out.println("Enter song name to remove: ");
        String title = input.next();

        if (playlist.isAlreadyInPlaylist(song)) {
            playlist.removeSong(song);
        } else {
            System.out.println("Song is not in the playlist yet...");
        }
    }


    // EFFECTS: plays next song on playlist
    private void playNext() {
        if (iterator.hasNext()) {
            System.out.println("Now playing " + playlist.playNext().toString());
        } else {
            System.out.println("The playlist has ended. Please add more songs.");
        }
    }

    // EFFECTS: plays previous song on playlist
    private void playPrev() {
        System.out.println("Now playing " + playlist.playPrev().toString());
    }

    // EFFECTS: replays current song on playlist
    private void replay() {
        System.out.println("Now playing " + playlist.replay().toString());

    }

    // EFFECTS: shows all songs of the playlist
    private void printPlaylist() {
        System.out.println("Playlist: ");

        //  Iterator<Song> s = playlist.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}



