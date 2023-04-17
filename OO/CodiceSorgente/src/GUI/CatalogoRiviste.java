package GUI;

import Controller.Controller;
import Model.Magazine;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Catalogo riviste.
 */
public class CatalogoRiviste {
    /**
     * The Frame.
     */
    JFrame frame;
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JComboBox periodicitBox;
    private JButton backButton;
    private JComboBox argumentBox;


    /**
     * Instantiates a new Catalogo riviste.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @throws SQLException the sql exception
     */
    public CatalogoRiviste(Controller controller, JFrame frameChiamante) throws SQLException {
        frame = new JFrame("CatalogoRiviste");
        frame.setContentPane(panel1);
        frame.getContentPane().setPreferredSize(new Dimension(1200, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


        createTextfield(controller);
        createArgumentBox(controller);
        createPeriodicitaBox(controller);
        createTable();
        ShowTable(controller);


        backButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
        argumentBox.addItemListener(new ItemListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    ShowTable(controller);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        periodicitBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        ShowTable(controller);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
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
     *
     * @param controller the controller
     * @throws SQLException the sql exception
     */
    void ShowTable(Controller controller) throws SQLException {
        String arg = String.valueOf(argumentBox.getSelectedItem());
        String period = String.valueOf(periodicitBox.getSelectedItem());
        String name = String.valueOf(textField1.getText());
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        ArrayList<Magazine> magazines = controller.readAllMagazines(period, arg, name);
        for (Magazine magazine : magazines) {
            model.addRow(new Object[]{magazine.getISSN_M(), magazine.getName(), magazine.getArgument(),
                    magazine.getManager(), magazine.getYearRelease(), magazine.getPublicationPeriod(),
                    magazine.getAccessMode(), magazine.getPublishingHouse()});
        }
    }


    /**
     * Create combo box.
     */
    private void createPeriodicitaBox(Controller controller) throws SQLException {
        ArrayList<String> periodicities = controller.getAllPerioditicitaMagazine();
        for (String i : periodicities) {
            periodicitBox.addItem(i);
        }
    }

    /**
     * Create argument box.
     *
     * @param controller the controller
     * @throws SQLException the sql exception
     */
    void createArgumentBox(Controller controller) throws SQLException {
        ArrayList<String> array = controller.getAllArgumentsMagazine();
        for (String i : array) {
            argumentBox.addItem(i);
        }
    }

    private void createTextfield(Controller controller) {
        textField1.getDocument().addDocumentListener(new DocumentListener() {
                                                         @Override
                                                         public void insertUpdate(DocumentEvent e) {
                                                             try {
                                                                 ShowTable(controller);
                                                             } catch (SQLException ex) {
                                                                 throw new RuntimeException(ex);
                                                             }
                                                         }

                                                         @Override
                                                         public void removeUpdate(DocumentEvent e) {
                                                             try {
                                                                 ShowTable(controller);
                                                             } catch (SQLException ex) {
                                                                 throw new RuntimeException(ex);
                                                             }
                                                         }

                                                         @Override
                                                         public void changedUpdate(DocumentEvent e) {
                                                             try {
                                                                 ShowTable(controller);
                                                             } catch (SQLException ex) {
                                                                 throw new RuntimeException(ex);
                                                             }
                                                         }
                                                     }
        );
    }
}
