package ui;

import model.Playlist;
import model.Song;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SongPanel extends JPanel {
    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private JButton addButton;
    private JButton removeButton;
    private JButton shuffleButton;
    private JList list;
    private DefaultListModel listModel;
    private JTextField title;
    private JTextField artist;
    private Playlist playlist = new Playlist();

    public SongPanel() {

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

            //User didn't type in a name
            if (songName.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                title.requestFocusInWindow();
                title.selectAll();
                return;
            }

            //User didn't type in an artist
            if (artistName.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                artist.requestFocusInWindow();
                artist.selectAll();
                return;
            }

            //User didn't type in a unique song
            if (alreadyInList(songName, artistName)) {
                Toolkit.getDefaultToolkit().beep();
                title.requestFocusInWindow();
                artist.requestFocusInWindow();
                title.selectAll();
                artist.selectAll();
                return;
            }

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

        // EFFECTS:
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }
}
