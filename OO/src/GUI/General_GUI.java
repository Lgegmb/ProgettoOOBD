package GUI;

import javax.swing.*;

public class General_GUI {

    private JFrame frame;

    public General_GUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
    }
}
