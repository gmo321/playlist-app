package ui;

import model.Song;
import model.Playlist;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// UI Functionality and methods are implemented from Teller App. Link below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Methods are implemented with reference to JsonSerializationDemo, link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Playlist application
public class PlaylistApp {
    private static final String JSON_STORE = "./data/playlist.json";
    private Playlist playlist;
    private Song song;
    private Scanner input;
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;


    //EFFECTS: Runs the playlist application
    public PlaylistApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPlaylist();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPlaylist() {
        boolean keepGoing = true;
        String command = null;

        initialize();

//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("a")) {
//            addSongToPlaylist();
//        } else if (command.equals("r")) {
//            removeSongFromPlaylist();
//        } else if (command.equals("n")) {
//            playNextSong();
//        } else if (command.equals("p")) {
//            playPrevSong();
//        } else if (command.equals("replay")) {
//            replaySong();
//        } else if (command.equals("s")) {
//            printPlaylist();
//        } else if (command.equals("save")) {
//            savePlaylist();
//        } else if (command.equals("l")) {
//            loadPlaylist();
//        } else {
//            System.out.println("Please try again");
//        }
//    }

    // MODIFIES: this
    // EFFECTS: initializes Playlist
    private void initialize() {
        playlist = new Playlist();
        song = new Song("ABC", "John");
        input = new Scanner(System.in);
    }

    // EFFECTS: Displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\ta -> add song");
//        System.out.println("\tr -> remove song");
//        System.out.println("\tn -> play next song");
//        System.out.println("\tp -> play previous song");
//        System.out.println("\treplay -> replay current song");
//        System.out.println("\ts -> show all songs");
//        System.out.println("\tsave -> save playlist to file");
//        System.out.println("\tl -> load playlist from file");
//        System.out.println("\tq -> quit");
//    }

    // MODIFIES: this
    // EFFECTS: Adds song to the playlist
    public void addSongToPlaylist() {
//        System.out.println("Enter song name to add: ");
//        String title = input.next();
//        System.out.println("Enter the artist of the song: ");
//        String artist = input.next();
        String title = song.getTitle();
        String artist = song.getArtist();

        Song newSong = new Song(title, artist);
        if (playlist.addSong(newSong)) {
            System.out.println(newSong.getTitle() + " by " + newSong.getArtist() + " was added to the playlist.");
            printCurrentSong();
        } else {
            System.out.println(newSong.getTitle() + " by " + newSong.getArtist() + " is already in playlist...");
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes song from the playlist
    private void removeSongFromPlaylist() {
//        System.out.println("Enter song name to remove: ");
//        String title = input.next();
//        System.out.println("Enter the artist of the song to remove: ");
//        String artist = input.next();
        String title = new String();
        String artist = new String();

        Song newSong = new Song(title, artist);
        if (playlist.removeSong(newSong)) {
            System.out.println(newSong.getTitle() + " by " + newSong.getArtist() + " was removed from the playlist.");
            printCurrentSong();
        } else {
            System.out.println(newSong.getTitle() + " by " + newSong.getArtist() + " is not in the playlist yet...");
        }
    }

    // EFFECTS: Given the current song, plays the next song on playlist
    private void playNextSong() {
        Song newSong = playlist.getCurrentSong();

        if (playlist.hasNextSong(newSong)) {
            System.out.println("Now playing " + playlist.playNext());
        } else {
            System.out.println("The playlist has ended. Please add more songs.");
        }
    }

    // EFFECTS: Given the current song, plays the previous song on playlist
    private void playPrevSong() {
        Song newSong = playlist.getCurrentSong();

        if (playlist.hasPrevSong(newSong)) {
            System.out.println("Now playing " + playlist.playPrev());
        } else {
            System.out.println("There is no previous song.");
        }
    }

    // EFFECTS: Replays the current song on playlist
    private void replaySong() {
        if (playlist.isEmpty()) {
            System.out.println("There is no song on the playlist.");
        } else {
            System.out.println("Now playing " + playlist.getCurrentSong());
        }
    }

    // EFFECTS: Prints out a list of all songs on the playlist
    private void printPlaylist() {
        List<Song> songs = playlist.getSongs();

        System.out.println("Playlist: ");
        for (Song s : songs) {
            System.out.println(s);
        }
    }

    // EFFECTS: Prints out the current song playing in playlist
    private void printCurrentSong() {
        if (!playlist.isEmpty()) {
            System.out.println("The current song playing is: " + playlist.getCurrentSong());
        } else {
            System.out.println("There is no song on the playlist.");
        }
    }

    // EFFECTS: saves the playlist to file
    public void savePlaylist() {
        try {
            jsonWriter.open();
            jsonWriter.write(playlist);
            jsonWriter.close();
            System.out.println("Saved " + playlist + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads playlist from file
    public void loadPlaylist() {
        try {
            playlist = jsonReader.read();
            System.out.println("Loaded " + playlist + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}



