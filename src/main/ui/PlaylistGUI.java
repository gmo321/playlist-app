package ui;

import jdk.nashorn.internal.parser.JSONParser;
import model.Playlist;
import model.Song;


import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.*;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

public class PlaylistGUI extends JPanel implements ListSelectionListener {

    private static final int WIDTH = 540;
    private static final int HEIGHT = 450;
    private Playlist playlist = new Playlist();
    private Song song;
    private JList list;
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
    private String song2 = "Lovesick Girls";
    private String artist2 = "Blackpink";
    private String song3 = "Good Days";
    private String artist3 = "SZA";

    @SuppressWarnings("checkstyle:MethodLength")
    public PlaylistGUI() {
        super(new BorderLayout());


        listModel = new DefaultListModel();
        listModel.addElement(song1 + " by " + artist1);
        listModel.addElement(song2 + " by " + artist2);
        listModel.addElement(song3 + " by " + artist3);


        Song songOne = new Song(song1, artist1);
        playlist.addSong(songOne);
        Song songTwo = new Song(song2, artist2);
        playlist.addSong(songTwo);
        Song songThree = new Song(song3, artist3);
        playlist.addSong(songThree);



        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);


        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        shuffleButton = new JButton("Shuffle");
        shuffleButton.setActionCommand("Shuffle");
        shuffleButton.addActionListener(new ShuffleListener());


        JLabel songLabel = new JLabel("Enter title");
        title = new JTextField(10);
        title.addActionListener(addListener);
        title.getDocument().addDocumentListener(addListener);
        String name = listModel.getElementAt(
                list.getSelectedIndex()).toString();


        JLabel artistLabel = new JLabel("Enter artist");
        artist = new JTextField(10);
        artist.addActionListener(addListener);
        artist.getDocument().addDocumentListener(addListener);
        String aname = listModel.getElementAt(
                list.getSelectedIndex()).toString();

        //  JLabel currentSongLabel = new JLabel("Current song playing: ");


        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        mb.add(fileMenu);
        JMenuItem openButton = new JMenuItem("Open");
        JMenuItem saveButton = new JMenuItem("Save");


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
                    ioException.printStackTrace();
                }
            }
        });


        // EFFECTS: Saves the playlist to JSON file
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(playlist);
                    jsonWriter.close();
                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
            });

        fileMenu.add(openButton);
        fileMenu.add(saveButton);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BorderLayout());
        //       buttonPane.setLayout(new GridLayout(2,3));\


        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
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


        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        //  JPanel currentSongPanel = new JPanel();
        //  currentSongPanel.add(currentSongLabel);
        // buttonPane.add(currentSongPanel, BorderLayout.PAGE_END);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        add(mb, BorderLayout.NORTH);


    }


    //todo figure out how to connect this with my playlistapp methods
    //todo song and artist dupes not working

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


            //User didn't type in a unique name...
            if (songName.equals("") || artistName.equals("")
                    || alreadyInList(songName, artistName)) {
                Toolkit.getDefaultToolkit().beep();
                title.requestFocusInWindow();
                title.selectAll();
                artist.requestFocusInWindow();
                artist.selectAll();
                return;
            }


            //plays beep when song + artist added
            //Toolkit.getDefaultToolkit().beep();

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(title.getText() + " by " + artist.getText(), index);

            Song song = new Song(title.getText(), artist.getText());

            playlist.addSong(song);
            System.out.println(playlist.getSongs());


            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(title.getText());


            //Reset the text field.
            title.requestFocusInWindow();
            title.setText("");

            //Reset the text field.
            artist.requestFocusInWindow();
            artist.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        //TODO not working properly
        protected boolean alreadyInList(String songName, String artistName) {
            return listModel.contains(songName) && listModel.contains(artistName);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }

    public static void actualGUI() {

        JFrame frame = new JFrame("Playlist Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        // JButton button = new JButton("Press");
        //  frame.getContentPane().add(button); // Adds Button to content pane of frame


        //Create and set up the content pane.
        JComponent newContentPane = new PlaylistGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                actualGUI();
            }
        });
    }
}




