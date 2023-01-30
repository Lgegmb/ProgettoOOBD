package GUI;

import ImplementazioneDAO.ImplementazioneMagazine;
import Model.Magazine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The type Catalogo riviste.
 */
public class CatalogoRiviste {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton cercaButton;
    private JComboBox periodicitBox;
    private JButton backButton;


    /**
     * Instantiates a new Catalogo riviste.
     */
    public CatalogoRiviste() {
        JFrame frame = new JFrame("CatalogoRiviste");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        createComboBox();
        createTable();
        ShowTable();


        cercaButton.addMouseListener(new MouseAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = textField1.getText();
                ImplementazioneMagazine implementazioneMagazine = new ImplementazioneMagazine();
                ArrayList<Magazine> magazines = implementazioneMagazine.searchByMagazineName(name);
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
                for (Magazine magazine : magazines) {
                    model.addRow(new Object[]{magazine.getISSN_M(), magazine.getName(), magazine.getArgument(), magazine.getManager(),
                            magazine.getYearRelease(), magazine.getPublicationPeriod(), magazine.getAccessMode(), magazine.getPublishingHouse()});
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new StartPage();
            }
        });
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new CatalogoRiviste();
    }

    /**
     * Gets panel 1.
     *
     * @return the panel 1
     */
    public JPanel getPanel1() {
        return panel1;
    }

    /**
     * Create table.
     */
    void createTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ISSN Magazine");
        model.addColumn("Name Magazine");
        model.addColumn("Argument");
        model.addColumn("Manager");
        model.addColumn("YearRelease");
        model.addColumn("PublicationPeriod");
        model.addColumn("AccessMode");
        model.addColumn("PublishingHouse");
        table1.setModel(model);

        TableColumnModel cols = table1.getColumnModel();
        cols.getColumn(0);
        cols.getColumn(1);
        cols.getColumn(2);
        cols.getColumn(3);
        cols.getColumn(4);
        cols.getColumn(5);
        cols.getColumn(6);
        cols.getColumn(7);
    }

    /**
     * Show table.
     */
    void ShowTable() {

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        ImplementazioneMagazine implementazioneMagazine = new ImplementazioneMagazine();
        ArrayList<Magazine> magazines = implementazioneMagazine.readAll();
        for (Magazine magazine : magazines) {
            model.addRow(new Object[]{magazine.getISSN_M(), magazine.getName(), magazine.getArgument(), magazine.getManager(), magazine.getYearRelease(), magazine.getPublicationPeriod(), magazine.getAccessMode(), magazine.getPublishingHouse()});
        }
    }


    /**
     * Create combo box.
     */
    private void createComboBox() {
        String[] periodicities;
        ImplementazioneMagazine implementazioneMagazine = new ImplementazioneMagazine();
        periodicities = implementazioneMagazine.getAllPeriodicities().toArray(new String[0]);
        for (String i  : periodicities) {
            periodicitBox.addItem(i);
        }
        periodicitBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    ShowTable();
                }
            }
        });
    }
}
