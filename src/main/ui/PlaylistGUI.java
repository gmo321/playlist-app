package ui;

import exceptions.SongAlreadyInPlaylistException;
import model.Playlist;
import model.Song;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;

import persistence.JsonReader;
import persistence.JsonWriter;

// GUI is implemented with reference to JListDemo, link below:
//https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples
// /components/ListDemoProject/src/components/ListDemo.java
// JMenuBar is implemented with reference to example, link below:
// https://www.guru99.com/java-swing-gui.html#9
// shuffleButton.AddActionListener is implemented with reference to example, link below:
// https://stackoverflow.com/questions/17064599/shuffle-defaultlistmodel

// Represents a Playlist application as GUI which has the functions of adding, removing, shuffling songs,
// and saving and loading playlists
public class PlaylistGUI extends JPanel implements ListSelectionListener {
    private Playlist playlist = new Playlist();
    private Song song;
    private JList list;
    private JPanel buttonPane;
    private DefaultListModel listModel;
    protected JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    protected JsonReader jsonReader = new JsonReader(JSON_STORE);
    private static final String JSON_STORE = "./data/playlist.json";
    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private JButton addButton;
    private JButton removeButton;
    private JButton shuffleButton;
    private JTextField title;
    private JTextField artist;
    private String song1 = "Hello";
    private String artist1 = "Adele";
    private String song2 = "305";
    private String artist2 = "Shawn Mendes";
    private String song3 = "Good Days";
    private String artist3 = "SZA";

    // Constructs a GUI for a playlist application
    public PlaylistGUI() throws SongAlreadyInPlaylistException {
        super(new BorderLayout());
        listModel = new DefaultListModel();
        addSongs();
        createSongs();
        initList();
        JScrollPane listScrollPane = new JScrollPane(list);
        AddListener addListener = initAddButton();
        initRemoveButton();
        initShuffleButton();

        JLabel songLabel = new JLabel("Enter title");
        initTitleField(addListener);
        JLabel artistLabel = new JLabel("Enter artist");
        initArtistField(addListener);

        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        mb.add(fileMenu);
        JMenuItem openButton = new JMenuItem("Open");
        JMenuItem saveButton = new JMenuItem("Save");

        initPanel(listScrollPane, songLabel, artistLabel, mb, fileMenu, openButton, saveButton);
    }

    // EFFECTS: Initializes artist text field
    private void initArtistField(AddListener addListener) {
        artist = new JTextField(10);
        artist.addActionListener(addListener);
        artist.getDocument().addDocumentListener(addListener);
    }

    // EFFECTS: Initializes title text field
    private void initTitleField(AddListener addListener) {
        title = new JTextField(10);
        title.addActionListener(addListener);
        title.getDocument().addDocumentListener(addListener);
    }

    // EFFECTS: Adds default songs to listModel
    // MODIFIES: this
    private void addSongs() {
        listModel.addElement(song1 + " by " + artist1);
        listModel.addElement(song2 + " by " + artist2);
        listModel.addElement(song3 + " by " + artist3);
    }


    // EFFECTS: Initializes button panel with text fields and buttons
    private void initPanel(JScrollPane listScrollPane, JLabel songLabel, JLabel artistLabel,
                           JMenuBar mb, JMenu fileMenu, JMenuItem openButton, JMenuItem saveButton) {
        buttonPane = new JPanel();
        buttonPane.setLayout(new BorderLayout());

        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPane.add(songLabel);
        buttonPane.add(title);
        buttonPane.add(artistLabel);
        buttonPane.add(artist);
        buttonPane.add(addButton);
        buttonPane.add(removeButton);
        buttonPane.add(shuffleButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        fileMenu.add(openButton);
        openButton.addActionListener(new OpenButton());
        fileMenu.add(saveButton);
        saveButton.addActionListener(new SaveButton());

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        add(mb, BorderLayout.NORTH);
    }

    // REQUIRES: playlist.json file
    // EFFECTS: Loads the playlist from JSON file and updates the GUI with that playlist
    // MODIFIES: this
    class OpenButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                openButtonListener();
            } catch (SongAlreadyInPlaylistException songAlreadyInPlaylistException) {
                songAlreadyInPlaylistException.printStackTrace();
            }
        }
    }

    // REQUIRES: Playlist is not empty
    // EFFECTS: Saves the playlist to JSON file
    class SaveButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveButtonListener();
        }
    }

    // EFFECTS: Initializes shuffle button
    private void initShuffleButton() {
        shuffleButton = new JButton("Shuffle");
        shuffleButton.setActionCommand("Shuffle");
        shuffleButton.addActionListener(new ShuffleListener());
    }

    // EFFECTS: Initializes remove button
    private void initRemoveButton() {
        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());
        removeButton.setEnabled(false);
    }

    // EFFECTS: Initializes add button
    private AddListener initAddButton() {
        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);
        return addListener;
    }

    // Initializes JList listModel
    private void initList() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
    }

    // EFFECTS: Saves the playlist to JSON file
    private void saveButtonListener() {
        try {
            jsonWriter.open();
            jsonWriter.write(playlist);
            jsonWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: Loads the playlist from JSON file and updates the GUI with that playlist
    // MODIFIES: this
    private void openButtonListener() throws SongAlreadyInPlaylistException {
        try {
            playlist = jsonReader.read();
            listModel.clear();
            for (Song playlistSong : playlist.getSongs()) {
                listModel.addElement(playlistSong.getTitle() + " by " + playlistSong.getArtist());
            }
        } catch (IOException ioException) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Creates 3 default song objects to be added to listModel and playlist
    // MODIFIES: this
    private void createSongs() throws SongAlreadyInPlaylistException {
        Song songOne = new Song(song1, artist1);
        playlist.addSong(songOne);
        Song songTwo = new Song(song2, artist2);
        playlist.addSong(songTwo);
        Song songThree = new Song(song3, artist3);
        playlist.addSong(songThree);
    }

    // REQUIRES: Songs to be in playlist
    // EFFECTS: Removes mouse's currently selected song from playlist
    // MODIFIES: this
    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // REQUIRES: Songs to be in the playlist
    // EFFECTS: ActionListener called Shuffle which shuffles the order of songs in the playlist
    // MODIFIES: this
    class ShuffleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < listModel.size(); i++) {
                int swapWith = (int) (Math.random() * (listModel.size() - i)) + i;
                if (swapWith == i) {
                    continue;
                }
                listModel.add(i, listModel.remove(swapWith));
                listModel.add(swapWith, listModel.remove(i + 1));
            }
        }
    }

    // EFFECTS: Adds songs to playlist; if mouse is currently selecting song, adds song after that selected song
    // MODIFIES: this
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            String songName = title.getText();
            String artistName = artist.getText();

            if (noInput(songName, title)) { // User didn't type in a name
                return;
            }

            if (noInput(artistName, artist)) { // User didn't type in an artist
                return;
            }

            if (notUniqueSong(songName, artistName)) { // User didn't type in a unique song
                return;
            }

            // Inserts song in listModel
            int index = addSongToList();

            listModel.insertElementAt(title.getText() + " by " + artist.getText(), index);

            try {
                addSongsToPlaylist();
            } catch (SongAlreadyInPlaylistException songE) {
                System.out.println("This song is already in the playlist");
            }

            // Reset the title text field.
            resetTitleTextField(title);

            // Reset the artist text field.
            resetTitleTextField(artist);

            // Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // REQUIRES: Song and artist to be in playlist
        // EFFECTS: Returns true if user song and artist are already in listModel, a sound is played and title and
        // artist text fields are highlighted
        private boolean notUniqueSong(String songName, String artistName) {
            if (alreadyInList(songName, artistName)) {
                Toolkit.getDefaultToolkit().beep();
                title.requestFocusInWindow();
                artist.requestFocusInWindow();
                title.selectAll();
                artist.selectAll();
                return true;
            }
            return false;
        }

        // EFFECTS: Returns true if song name and artist name are already in listModel
        protected boolean alreadyInList(String songName, String artistName) {
            return (listModel.contains(songName + " by " + artistName));
        }

        // EFFECTS: update for when there is an input, addButton is enabled
        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // EFFECTS: Update for when there is no inputs
        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // EFFECTS: If text is not empty, enable the addButton
        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // EFFECTS: Enables the addButton
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // EFFECTS: if user has not inputted anything, set buttons to false
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // EFFECTS: Adds song to listModel, if cursor selects an index, insert song after selected item
    // MODIFIES: this
    private int addSongToList() {
        int index = list.getSelectedIndex(); //get selected index
        if (index == -1) { //no selection, so insert at beginning
            index = 0;
        } else {           //add after the selected item
            index++;
        }
        return index;
    }

    // EFFECTS: Resets the title text field
    private void resetTitleTextField(JTextField title) {
        title.requestFocusInWindow();
        title.setText("");
    }

    // EFFECTS: Returns true if user does not input artist or song name
    private boolean noInput(String songName, JTextField title) {
        if (songName.equals("")) {
            Toolkit.getDefaultToolkit().beep();
            title.requestFocusInWindow();
            title.selectAll();
            return true;
        }
        return false;
    }

    // EFFECTS: Adds songs to playlist object whenever user adds to listModel
    // MODIFIES: this
    private void addSongsToPlaylist() throws SongAlreadyInPlaylistException {
        song = new Song(title.getText(), artist.getText());
        playlist.addSong(song);
        System.out.println(playlist.getSongs());
    }


    // EFFECTS: If no more events are happening, if there is no selection of element, disable remove button
    // else if there is a selection, enable to remove button
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                //No selection, disable remove button.
                removeButton.setEnabled(false);
            } else {
                //Selection, enable the remove button.
                removeButton.setEnabled(true);
            }
        }
    }
}




