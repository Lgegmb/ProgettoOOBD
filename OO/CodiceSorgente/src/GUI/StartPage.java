package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

/**
 * The type Start page.
 */
public class StartPage {
    private JPanel panel1;
    private JButton catalogoButton;
    private JButton documentazioneButton;
    private JButton lorenzoTecchiaButton;
    private JButton mirkoMarcianoButton;

    /**
     * Instantiates a new Start page.
     */


    public StartPage() {
        Controller controller = new Controller();
        JFrame frame = new JFrame("StartPage");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        catalogoButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //open the catalog
                frame.setVisible(false);
                EsploraCatalogo catalogo = new EsploraCatalogo(controller, frame);
                catalogo.frame.setVisible(true);
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
                //open the GitHub profile
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
                //open the GitHub profile
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Lgegmb"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}