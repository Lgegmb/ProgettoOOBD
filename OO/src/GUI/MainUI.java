package GUI;

import ImplementazioneDAO.ImplementazioneAuthor;
import Model.Author;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

public class MainUI {

    private JPanel rootPanel;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;


    public MainUI() {
        createTable();
        createGenreCombo();
        createTypeCombo();
        
//        for (Author author : authors) {
//            System.out.println(author.getFName());
//        }

    }


    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createTable() {
        Object[][] data = {
                {"Inception", 2008, 9.0, 188},
                {"Film_2", 1980, 8.8, 200},
                {"Film_3", 2023, 9.2, 100}
        };
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Titolo", "Year", "Rating", "NumVotes"}
        ));
        TableColumnModel cols = table1.getColumnModel();
        cols.getColumn(0).setMinWidth(250);
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        cols.getColumn(1).setCellRenderer(centerRender);
        cols.getColumn(2).setCellRenderer(centerRender);
        cols.getColumn(3).setCellRenderer(centerRender);

    }

    private void createGenreCombo() {
        comboBox1.setModel(new DefaultComboBoxModel(new String[]{"Action", "Fantasy", "Drama"}));
    }

    private void createTypeCombo() {
        comboBox2.setModel(new DefaultComboBoxModel(new String[]{"TvSeries", "Movie", "Short"}));
    }
}
