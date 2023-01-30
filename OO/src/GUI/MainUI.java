package GUI;

import ImplementazioneDAO.ImplementazioneAuthor;
import Model.Author;
import Controller.*;
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
        Controller controller = new Controller();
        ShowTable();
    }


    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void ShowTable() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        ImplementazioneAuthor implementazioneAuthor = new ImplementazioneAuthor();
        ArrayList<Author> authors = implementazioneAuthor.readAll();
        for (Author author : authors) {
            model.addRow(new Object[]{author.getFName(), author.getLName(), author.getCodauthor()});
        }
    }


    private void createTable() {


        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Author Name");
        model.addColumn("Author Surname");
        model.addColumn("Author Code");
        table1.setModel(model);

        TableColumnModel cols = table1.getColumnModel();
        cols.getColumn(0);
        cols.getColumn(1);
        cols.getColumn(2);

    }

    private void createGenreCombo() {

    }

    private void createTypeCombo() {

    }
}
