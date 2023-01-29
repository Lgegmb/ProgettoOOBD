package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.BibliographyDAO;
import DAO.BookDAO;
import Database.ConnessioneDatabase;

public class CatalogoLibri {
    private JPanel panel1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton cercaButton;
    private JScrollBar scrollBar1;
    private JList<Model.Book> list1;

    public CatalogoLibri() {
        addElements();
        JFrame frame = new JFrame("CatalogoLibri");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void ricercaLibro(){
        String query="Select * FROM libro where ";
        String selezione=comboBox1.getSelectedItem().toString();
        String scritto=textField1.getText();
        query=query.concat(selezione);
        query=query.concat("='");
        query=query.concat(scritto);
        query=query.concat("';");
        System.out.println(query);

    }

    //add elements to the Jlist
    private void addElements(){
        DefaultListModel<Model.Book> model = new DefaultListModel<>();
        try {
            Connection connection = ConnessioneDatabase.getInstance().connection;
            BibliographyDAO bibliographyDAO = new BibliographyDAO(connection);
            ArrayList listaLibri = bibliographyDAO.readAllBibliography();
            model.addAll(listaLibri);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list1.setModel(model);
    }

    public static void main(String[] args) {
        new CatalogoLibri();

    }
}
