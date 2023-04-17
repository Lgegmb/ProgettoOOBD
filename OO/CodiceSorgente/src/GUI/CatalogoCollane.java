package GUI;

import Controller.Controller;
import Model.Series;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Catalogo collane.
 */
public class CatalogoCollane {
    /**
     * The Frame.
     */
    JFrame frame;
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JComboBox editionBox;
    private JButton backButton;

    /**
     * Instantiates a new Catalogo collane.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @throws SQLException the sql exception
     */
    public CatalogoCollane(Controller controller, JFrame frameChiamante) throws SQLException {
        frame = new JFrame("CatalogoCollane");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        createTextfield(controller);
        createEditionBox(controller);
        createTable();
        showTable(controller);


        backButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frameChiamante.setVisible(true);
            }
        });
        editionBox.addItemListener(new ItemListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    showTable(controller);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    private void showTable(Controller controller) throws SQLException {

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        Integer edition = Integer.parseInt(editionBox.getSelectedItem().toString());
        String nameS = String.valueOf(textField1.getText());

        ArrayList<Series> series = controller.readAllSeries(edition,nameS);
        for (Series s : series) {
            model.addRow(new Object[]{s.getISSN_S(), s.getCurator(),
                    s.getEdition(), s.getNameS(), s.getCode()});
        }
    }

    private void createTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ISSN Series");
        model.addColumn("Curator");
        model.addColumn("Edition");
        model.addColumn("Name Series");
        model.addColumn("Code Series");
        table1.setModel(model);

        TableColumnModel columnModel = table1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);

    }

    private void createEditionBox(Controller controller) throws SQLException {
        ArrayList<String> edition = controller.getAllEdition();
        for (String s : edition) {
            editionBox.addItem(s);
        }

    }


    private void createTextfield(Controller controller) {
        textField1.getDocument().addDocumentListener(new DocumentListener() {
                                                         @Override
                                                         public void insertUpdate(DocumentEvent e) {
                                                             try {
                                                                 showTable(controller);
                                                             } catch (SQLException ex) {
                                                                 throw new RuntimeException(ex);
                                                             }
                                                         }

                                                         @Override
                                                         public void removeUpdate(DocumentEvent e) {
                                                             try {
                                                                 showTable(controller);
                                                             } catch (SQLException ex) {
                                                                 throw new RuntimeException(ex);
                                                             }
                                                         }

                                                         @Override
                                                         public void changedUpdate(DocumentEvent e) {
                                                             try {
                                                                 showTable(controller);
                                                             } catch (SQLException ex) {
                                                                 throw new RuntimeException(ex);
                                                             }
                                                         }
                                                     }
        );
    }

}
