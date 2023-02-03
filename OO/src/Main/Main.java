package Main;

import GUI.StartPage;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });

    }

    private static void createAndShowGUI() {

        new StartPage();
    }
}