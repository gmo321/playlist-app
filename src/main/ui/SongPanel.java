package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SongPanel extends JPanel {

    public SongPanel() {
        JPanel songPanel = new JPanel();
        FlowLayout layout = new FlowLayout();
        songPanel.setLayout(layout);

        JLabel songLabel = new JLabel("Enter title");
        JTextField tf = new JTextField(10);

        JLabel artistLabel = new JLabel("Enter artist");
        JTextField atf = new JTextField(10);

        JButton add = new JButton("Add");
        JButton remove = new JButton("Remove");
        songPanel.add(songLabel);
        songPanel.add(tf);
        songPanel.add(artistLabel);
        songPanel.add(atf);
    }



}
