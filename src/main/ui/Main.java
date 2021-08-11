package ui;

import exceptions.SongAlreadyInPlaylistException;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {
    private static final int WIDTH = 570;
    private static final int HEIGHT = 400;

    public static void main(String[] args) throws FileNotFoundException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PlaylistGUI actualGUI = null;
                try {
                    actualGUI = new PlaylistGUI();
                } catch (SongAlreadyInPlaylistException e) {
                    e.printStackTrace();
                }
                JFrame frame = new JFrame("Playlist Application");
                JPanel buttonPanel = new JPanel();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(WIDTH, HEIGHT);
                frame.getContentPane().add(actualGUI);
                frame.setVisible(true);
            }
        });
    }
}

//TODO: refactor
//TODO: Use hashmap
//TODO: make class robust, create exceptions
//TODO: update readme.md with changes
//TODO: draw UML diagram