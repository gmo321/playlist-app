package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {
    private static final int WIDTH = 570;
    private static final int HEIGHT = 320;

    public static void main(String[] args) throws FileNotFoundException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PlaylistGUI actualGUI = new PlaylistGUI();
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
