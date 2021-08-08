package ui;

import jdk.nashorn.internal.parser.JSONParser;
import model.Playlist;
import model.Song;


import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
import javax.xml.soap.Text;

import org.json.*;
import org.json.JSONObject;

public class PlaylistGUI extends JPanel implements ListSelectionListener {

    private static final int WIDTH = 540;
    private static final int HEIGHT = 650;
    private Playlist playlist;
    private Song song;
    private PlaylistApp playlistApp;
    private JFrame frame;
    private JList list;
    private DefaultListModel listModel;
    private JPanel panel;
    private JLabel label;


    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private JButton addButton;
    private JButton removeButton;
    private JTextField title;
    private JTextField artist;

    @SuppressWarnings("checkstyle:MethodLength")
    public PlaylistGUI() {
        super(new BorderLayout());

        listModel = new DefaultListModel();
        listModel.addElement("Hello by Adele");
        listModel.addElement("Lovesick Girls by Blackpink");
        listModel.addElement("Good Days by SZA");


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


        // JTextField tf = new JTextField(10);

        //JLabel artistLabel = new JLabel("Enter artist");
        // JTextField atf = new JTextField(10);

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
        // JMenu m2 = new JMenu("Help");
        mb.add(fileMenu);
        // mb.add(m2);
        JMenuItem openButton = new JMenuItem("Open");
//        openButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });

        JMenuItem saveButton = new JMenuItem("Save");
//        saveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JSONObject obj = new JSONObject();
//                obj.put("Title", this.title.getText());
//                obj.put("Artist", this.artist.getText());
//
//                JSONObject playlist = new JSONObject();
//                playlist.put("Playlist", obj);
//
//                JSONObject obj1 = new JSONObject();
//                obj1.put("Title", "Rude");
//                obj1.put("Artist", "Magic");
//
//                JSONObject playlist2 = new JSONObject();
//                playlist2.put("Playlist2", obj1);
//
//                JSONArray arr = new JSONArray();
//
//                arr.put(playlist);
//                arr.put(playlist2);
//
//                try (FileWriter Data = new FileWriter("Data.JSON")) {
//                    Data.write(arr.toString(4)); // setting spaces for indent
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });

        fileMenu.add(openButton);
        fileMenu.add(saveButton);
        //  setJMenuBar(mb);

        JPanel buttonPane = new JPanel();
        //       buttonPane.setLayout(new GridLayout(2,3));\
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(songLabel);
        buttonPane.add(title);
        buttonPane.add(artistLabel);
        buttonPane.add(artist);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(addButton);
        buttonPane.add(removeButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //    JPanel currentSongPanel = new JPanel();
        //   currentSongPanel.add(currentSongLabel);


        //    add(currentSongPanel, BorderLayout.PAGE_START);


        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        add(mb, BorderLayout.NORTH);


//        private void saveButtonListener(ActionEvent e) {
//            JSONObject obj = new JSONObject();
//            obj.put("Title", this.title.getText());
//            obj.put("Artist", this.artist.getText());
//
//            JSONObject playlist = new JSONObject();
//            playlist.put("Playlist", obj);
//
//            JSONObject obj1 = new JSONObject();
//            obj1.put("Title", "Rude");
//            obj1.put("Artist", "Magic");
//
//            JSONObject playlist2 = new JSONObject();
//            playlist2.put("Playlist2", obj1);
//
//            JSONArray arr = new JSONArray();
//
//            arr.put(playlist);
//            arr.put(playlist2);
//
//            try (FileWriter Data = new FileWriter("Data.JSON")) {
//                Data.write(arr.toString(4)); // setting spaces for indent
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
    }

//
//            JSONArray jrr = new JSONArray();
//            JSONParser jp = new JSONParser();
////        playlistApp.savePlaylist();

//            try {
//                FileReader file = new FileReader("Playlist.json");
//                jrr = (JSONArray)jp.parse(file);
//            } catch (Exception var7) {
//                JOptionPane.showMessageDialog((Component)null, "Error occured");
//            }
//
//
//            jrr.add(obj);
//
//            try {
//                FileWriter file = new FileWriter("Playlist.json");
//                file.write(jrr.toJSONString());
//                file.close();
//            } catch (Exception var6) {
//                JOptionPane.showMessageDialog((Component)null, "Error occured");
//            }
//
//            JOptionPane.showMessageDialog((Component)null, "Data Saved");
//        }


    //todo figure out how to connect this with my playlistapp methods
    //todo add a currently playing text
    //todo get file button to function
    //todo add panel at bottom with play, next, prev buttons and currently playing song?
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
            // String songAndArtist = title.getText() + "by" + artist.getText();

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

//            //User didn't type in a unique artist...
//            if (artistName.equals("") || alreadyInList(artistName)) {
//                Toolkit.getDefaultToolkit().beep();
//                artist.requestFocusInWindow();
//                artist.selectAll();
//                return;
//            }


            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt(title.getText() + " by " + artist.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(title.getText());

//            listModel.insertElementAt(artist.getText(), index);

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

        protected boolean alreadyInList(String title, String artist) {
            return listModel.contains(title) && listModel.contains(artist);
        }


        // @Override
        //public void actionPerformed(ActionEvent e) {

        // }

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


//
//        JFrame frame = new JFrame("Playlist Application");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(WIDTH,HEIGHT);
//        // JButton button = new JButton("Press");
//        //  frame.getContentPane().add(button); // Adds Button to content pane of frame
//        frame.setVisible(true);
//
//        //Creating the MenuBar and adding components
//        JMenuBar mb = new JMenuBar();
//        JMenu m1 = new JMenu("File");
//        // JMenu m2 = new JMenu("Help");
//        mb.add(m1);
//        // mb.add(m2);
//        JMenuItem m11 = new JMenuItem("Open");
//        JMenuItem m22 = new JMenuItem("Save as");
//        m1.add(m11);
//        m1.add(m22);
//
//
//        //Creating the panel at bottom and adding components
//        JPanel panel = new JPanel(); // the panel is not visible in output
//        JLabel label = new JLabel("Enter Song");
//        JTextField tf = new JTextField(10); // accepts up to 10 characters
//        JButton add = new JButton("Add");
//        JButton remove = new JButton("Remove");
//        panel.add(label); // Components Added using Flow Layout
//        panel.add(tf);
//        panel.add(add);
//        panel.add(remove);
//
//        JPanel p = new JPanel();
//        JLabel artistLabel = new JLabel("Enter Artist");
//        JTextField text = new JTextField(10);
//        p.add(artistLabel); // Components Added using Flow Layout
//        p.add(text);
//
//
//        JTextArea ta = new JTextArea();
//
//        //Adding Components to the frame.
//        frame.getContentPane().add(BorderLayout.SOUTH, panel);
//        frame.getContentPane().add(BorderLayout.NORTH, mb);
//        frame.getContentPane().add(BorderLayout.CENTER, ta);
//        frame.setVisible(true);
//        frame.getContentPane().add(BorderLayout.SOUTH, p);
//    }

//    public static void main(String[] args) {
//        new PlaylistGUI();
//
//    }

    // @Override
    // public void actionPerformed(ActionEvent e) {

//    }

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


//        JFrame frame = new JFrame("ListDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new PlaylistGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        // frame.pack();
        // frame.setVisible(true);
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


// JPanel panel = new Jpanel();

