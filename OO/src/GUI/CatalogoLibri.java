package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatalogoLibri {
    private JPanel panel1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton cercaButton;
    private JTable table1;

    public CatalogoLibri() {
        JFrame frame = new JFrame("CatalogoLibri");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        cercaButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ricercaLibro();
            }
        });
    }

    private void ricercaLibro() {
        String query = "Select * FROM libro where ";
        String selezione = comboBox1.getSelectedItem().toString();
        String scritto = textField1.getText();
        query = query.concat(selezione);
        query = query.concat("='");
        query = query.concat(scritto);
        query = query.concat("';");
        System.out.println(query);

    }

    public static void main(String[] args) {
        new CatalogoLibri();

    }
}
