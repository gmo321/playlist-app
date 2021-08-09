package ui;

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

    public SongPanel() {
//        addButton = new JButton(addString);
//        PlaylistGUI.AddListener addListener = new PlaylistGUI.AddListener(addButton);
//        addButton.setActionCommand(addString);
//        addButton.addActionListener(addListener);
//        addButton.setEnabled(false);
//
//        removeButton = new JButton(removeString);
//        removeButton.setActionCommand(removeString);
//        removeButton.addActionListener(new PlaylistGUI.RemoveListener());
//
//        shuffleButton = new JButton("Shuffle");
//        shuffleButton.setActionCommand("Shuffle");
//        shuffleButton.addActionListener(new PlaylistGUI.ShuffleListener());
//
//
//        JLabel songLabel = new JLabel("Enter title");
//        title = new JTextField(10);
//        title.addActionListener(addListener);
//        title.getDocument().addDocumentListener(addListener);
//        String name = listModel.getElementAt(
//                list.getSelectedIndex()).toString();
//
//
//        JLabel artistLabel = new JLabel("Enter artist");
//        artist = new JTextField(10);
//        artist.addActionListener(addListener);
//        artist.getDocument().addDocumentListener(addListener);
//        String aname = listModel.getElementAt(list.getSelectedIndex()).toString();
//
//        JPanel buttonPane = new JPanel();
//        //       buttonPane.setLayout(new GridLayout(2,3));\
//        buttonPane.setLayout(new BoxLayout(buttonPane,
//                BoxLayout.LINE_AXIS));
//        buttonPane.add(songLabel);
//        buttonPane.add(title);
//        buttonPane.add(artistLabel);
//        buttonPane.add(artist);
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(addButton);
//        buttonPane.add(removeButton);
//        buttonPane.add(shuffleButton);
//        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//    }


    }
}
