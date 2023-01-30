package GUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Esplora catalogo.
 */
public class EsploraCatalogo {
    private JButton bookButton;
    private JButton seriesButton;
    private JButton magazineButton;
    private JButton articleButton;
    private JButton backButton;
    private JPanel panel1;

    /**
     * Instantiates a new Esplora catalogo.
     */
    public EsploraCatalogo() {
        JFrame frame = new JFrame("EsploraCatalogo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(frame.getParent());
        frame.pack();
        frame.setVisible(true);

        backButton.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new StartPage();
            }
        });

        bookButton.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CatalogoLibri();
            }
        });

        seriesButton.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CatalogoCollane();
            }
        });

        magazineButton.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CatalogoRiviste();
            }
        });

        articleButton.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CatalogoArticoli();
            }
        });
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

    }
}