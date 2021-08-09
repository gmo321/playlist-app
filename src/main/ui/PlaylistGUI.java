package ui;

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

public class PlaylistGUI extends JPanel implements ListSelectionListener {

    private Playlist playlist = new Playlist();
    private Song song;
    private JList list;
    private JPanel buttonPane = new JPanel();
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

    @SuppressWarnings("checkstyle:MethodLength")
    public PlaylistGUI() {
        super(new BorderLayout());

        listModel = new DefaultListModel();

        // Adds default 3 items to JList
        listModel.addElement(song1 + " by " + artist1);
        listModel.addElement(song2 + " by " + artist2);
        listModel.addElement(song3 + " by " + artist3);

        Song songOne = new Song(song1, artist1);
        playlist.addSong(songOne);
        Song songTwo = new Song(song2, artist2);
        playlist.addSong(songTwo);
        Song songThree = new Song(song3, artist3);
        playlist.addSong(songThree);


        // Initializes JList
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);


        // Initializes addButton
        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        // Initializes removeButton
        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());
        removeButton.setEnabled(false);

        // Initializes shuffleButton
        shuffleButton = new JButton("Shuffle");
        shuffleButton.setActionCommand("Shuffle");
        shuffleButton.addActionListener(new ShuffleListener());


        // Creates label and text field for entering song title
        JLabel songLabel = new JLabel("Enter title");
        title = new JTextField(10);
        title.addActionListener(addListener);
        title.getDocument().addDocumentListener(addListener);
        String name = listModel.getElementAt(
                list.getSelectedIndex()).toString();


        // Creates label and text field for entering artist
        JLabel artistLabel = new JLabel("Enter artist");
        artist = new JTextField(10);
        artist.addActionListener(addListener);
        artist.getDocument().addDocumentListener(addListener);
        String aname = listModel.getElementAt(
                list.getSelectedIndex()).toString();

        //  JLabel currentSongLabel = new JLabel("Current song playing: ");


        // Creating the MenuBar and adding Open and Save components
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        mb.add(fileMenu);
        JMenuItem openButton = new JMenuItem("Open");
        JMenuItem saveButton = new JMenuItem("Save");


        // REQUIRES: playlist.json file
        // EFFECTS: Loads the playlist from JSON file and updates the GUI with that playlist
        // MODIFIES: this
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        // REQUIRES: Playlist is not empty
        // EFFECTS: Saves the playlist to JSON file
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(playlist);
                    jsonWriter.close();
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
            });

        // Initializes button panel
        JPanel buttonPane = new JPanel();
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
        fileMenu.add(saveButton);

        //  JPanel currentSongPanel = new JPanel();
        //  currentSongPanel.add(currentSongLabel);
        // buttonPane.add(currentSongPanel, BorderLayout.PAGE_END);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        add(mb, BorderLayout.NORTH);

    }


    //todo figure out how to connect this with my playlistapp methods

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

    // REQUIRES: Songs to be in playlist
    // EFFECTS: Adds songs to playlist; if mouse is currently selecting song, adds song after that selected song
    // MODIFIES: this
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        @SuppressWarnings("checkstyle:MethodLength")
        public void actionPerformed(ActionEvent e) {
            String songName = title.getText();
            String artistName = artist.getText();

            // User didn't type in a name
            if (songName.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                title.requestFocusInWindow();
                title.selectAll();
                return;
            }

            // User didn't type in an artist
            if (artistName.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                artist.requestFocusInWindow();
                artist.selectAll();
                return;
            }

            // User didn't type in a unique song
            if (alreadyInList(songName, artistName)) {
                Toolkit.getDefaultToolkit().beep();
                title.requestFocusInWindow();
                artist.requestFocusInWindow();
                title.selectAll();
                artist.selectAll();
                return;
            }

            // Inserts song in listModel
            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(title.getText() + " by " + artist.getText(), index);

            // Adds songs to playlist object
            Song song = new Song(title.getText(), artist.getText());
            playlist.addSong(song);
            System.out.println(playlist.getSongs());

            // Reset the title text field.
            title.requestFocusInWindow();
            title.setText("");

            // Reset the artist text field.
            artist.requestFocusInWindow();
            artist.setText("");

            // Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }


        // EFFECTS: Returns true if song name and artist name are already in listModel
        protected boolean alreadyInList(String songName, String artistName) {
            return (listModel.contains(songName + " by " + artistName));
        }

        //Required by DocumentListener.
        // EFFECTS: update for when there is an input, addButton is enabled
        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        // EFFECTS: Update for when there is no inputs
        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
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




