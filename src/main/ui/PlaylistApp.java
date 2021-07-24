package ui;

import model.Song;
import model.Playlist;

import java.util.Scanner;

// UI Functionality and methods are implemented from Teller App. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Playlist application
public class PlaylistApp {
    private Playlist list;
    private Song title;
    private Scanner input;

    //EFFECTS: runs the to do application
    public PlaylistApp() {
        runPlaylist();
    }

    private void runPlaylist() {
        boolean keepGoing = true;
        String command = null;

        init();

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
        } else if (command.equals("delete")) {
            removeSong();
        } else if (command.equals("complete")) {
            displayPlaylist();
        } else {
            System.out.println("Please try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes To-Do list
    private void initialize() {
        list = new Playlist();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> add song");
        System.out.println("\tremove -> remove song");
        System.out.println("\tshow -> show all songs");
        System.out.println("\tnext -> play next song");
        System.out.println("\tprev -> play previous song");
        System.out.println("\treplay -> replay current song");
        System.out.println("\tskip -> skip current song");
    }

    // MODIFIES: this
    // EFFECTS: adds a new song to the playlist
    private void addSong() {

    }

    // MODIFIES: this
    // EFFECTS: removes song from the playlist
    private void removeSong() {

    }

    // EFFECTS: shows all songs of the playlist
    private void displayPlaylist() {

    }

    // EFFECTS: plays next song on playlist
    private void playNext() {

    }

    // EFFECTS: plays previous song on playlist
    private void playPrev() {

    }

    // EFFECTS: replays current song on playlist
    private void replay() {

    }

    // EFFECTS: skips song on playlist
    private void skip() {

    }


}
