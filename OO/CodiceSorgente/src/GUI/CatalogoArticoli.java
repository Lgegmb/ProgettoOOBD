package GUI;

import Controller.Controller;
import Model.Article;

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
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * The type Catalogo riviste.
 */
public class CatalogoArticoli {
    /**
     * The Frame.
     */
    JFrame frame;
    private JPanel panel1;
    private JTable table1;
    private JComboBox topicsBox;
    private JTextField textField1;
    private JButton backButton;

    /**
     * Instantiates a new Catalogo articoli.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @throws SQLException the sql exception
     */
    public CatalogoArticoli(Controller controller, JFrame frameChiamante) throws SQLException {
        frame = new JFrame("CatalogoArticoli");
        frame.setContentPane(panel1);
        frame.getContentPane().setPreferredSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        createTextField(controller);
        createTopicsBOx(controller);
        createTable();
        ShowTable(controller);


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
        model.addColumn("Doi Article");
        model.addColumn("Title");
        model.addColumn("AcccessMode");
        model.addColumn("Editor");
        model.addColumn("Topic");
        model.addColumn("ReleaseDate");
        model.addColumn("ReleaseLocation");
        model.addColumn("ConferenceName");
        model.addColumn("Author");
        model.addColumn("Magazine Name");

        table1.setModel(model);
        TableColumnModel columnModel = table1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(100);
        columnModel.getColumn(8).setPreferredWidth(100);
        columnModel.getColumn(9).setPreferredWidth(100);

    }

    /**
     * Show table.
     *
     * @param controller the controller
     * @throws SQLException the sql exception
     */
    void ShowTable(Controller controller) throws SQLException {
        String topic = String.valueOf(topicsBox.getSelectedItem());
        String title = textField1.getText();
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        ArrayList<Article> articles = controller.readAllArticles(topic, title);
        for (Article article : articles) {
            model.addRow(new Object[]{article.getDoi_A(), article.getTitle(), article.getAccessMode(), article.getEditor()
                    , article.getTopic(), article.getReleaseDate(), article.getReleaseLocation(), article.getConferenceName(), article.getAuthor(), article.getFK_Magazine()});
        }
    }

    /**
     * Create combo box.
     */
    private void createTopicsBOx(Controller controller) throws SQLException {
        ArrayList<String> topics = controller.getAllTopicsArticle();
        for (String i : topics) {
            topicsBox.addItem(i);
        }
        topicsBox.addItemListener(new ItemListener() {
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

    private void createTextField(Controller controller) {
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