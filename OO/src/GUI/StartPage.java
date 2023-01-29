package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class StartPage {
    private JPanel panel1;
    private JButton prestitiButton;
    //fill the jpanel with an image
    private JButton gestioneButton;
    private JButton catalogoButton;
    private JButton documentazioneButton;
    private JButton lorenzoTecchiaButton;
    private JButton mirkoMarcianoButton;

    public StartPage(){

        JFrame frame = new JFrame("StartPage");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        prestitiButton.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
            }
        });
        gestioneButton.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
            }
        });

        catalogoButton.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                new EsploraCatalogo();

            }
        });

        documentazioneButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //open the documentation
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/lorenzotecchia/ProgettoOOBD/tree/main/OO/Documentazione"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        lorenzoTecchiaButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //open the github profile
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/lorenzotecchia"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        mirkoMarcianoButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //open the github profile
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Lgegmbh"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {
        new StartPage();
    }
}