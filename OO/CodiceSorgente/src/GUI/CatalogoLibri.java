package GUI;

import Controller.Controller;
import Model.Book;

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
 * The type Catalogo libri.
 */
public class CatalogoLibri {
    /**
     * The Frame.
     */
    JFrame frame;
    private JPanel panel1;
    private JTextField titleTextField;
    private JTable table1;
    private JButton backButton;
    private JComboBox argumentBox;
    private JComboBox reprintBox;
    private JComboBox languageBox;
    private JComboBox accessBox;
    private JTextField lnameTextField;

    /**
     * Instantiates a new Catalogo libri.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     * @throws SQLException the sql exception
     */
    public CatalogoLibri(Controller controller, JFrame frameChiamante) throws SQLException {
        frame = new JFrame("CatalogoLibri");
        frame.setContentPane(panel1);
        frame.getContentPane().setPreferredSize(new Dimension(1400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        createTextfield(controller);
        createReprintBox();
        createArgsBox(controller);
        createLanguageBox(controller);
        createAccessBox(controller);
        createTable();
        showTable(controller);


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
        accessBox.addItemListener(new ItemListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    showTable(controller);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        languageBox.addItemListener(new ItemListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    showTable(controller);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        argumentBox.addItemListener(new ItemListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    showTable(controller);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        reprintBox.addItemListener(new ItemListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    showTable(controller);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
    }

    private void createTextfield(Controller controller) {
<<<<<<< Updated upstream:OO/CodiceSorgente/src/GUI/CatalogoLibri.java
        titleTextField.getDocument().addDocumentListener(new DocumentListener() {
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

        lnameTextField.getDocument().addDocumentListener(new DocumentListener() {
=======

        textField1.getDocument().addDocumentListener(new DocumentListener() {
>>>>>>> Stashed changes:OO/src/GUI/CatalogoLibri.java
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
        textField2.getDocument().addDocumentListener(new DocumentListener() {
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

    private void createAccessBox(Controller controller) throws SQLException {
        ArrayList<String> access = controller.getAllAccessBook();
        for (String acc : access) {
            accessBox.addItem(acc);
        }
    }

    private void createLanguageBox(Controller controller) throws SQLException {
        ArrayList<String> langs = controller.getAllLanguagesBook();
        for (String lang : langs) {
            languageBox.addItem(lang);
        }
    }

    private void createArgsBox(Controller controller) throws SQLException {
        ArrayList<String> args = controller.getAllArgumentsBook();
        for (String arg : args) {
            argumentBox.addItem(arg);
        }
    }

    private void showTable(Controller controller) throws SQLException {
        String arg = String.valueOf(argumentBox.getSelectedItem());
        String lang = String.valueOf(languageBox.getSelectedItem());
        String access = String.valueOf(accessBox.getSelectedItem());
        Boolean reprint = Boolean.valueOf(String.valueOf(reprintBox.getSelectedItem()));
        String title = String.valueOf(titleTextField.getText());
        String author = String.valueOf(lnameTextField.getText());
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        ArrayList<Book> books = controller.readAllBooks(arg, lang, access, reprint, title, author);
        for (Book book : books) {
            model.addRow(new Object[]{book.getISBN_B(), book.getPublishingHouse(), book.getLanguage(),
                    book.getAccessMode(), book.getTitle(), book.getArgument(), book.getReprint(), book.getReleaseDate(),
                    book.getReleaseLocation(), book.getPresentationName(), book.getFK_Series(), book.getAuthor()});
        }

    }

    private void createTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ISBN Book");
        model.addColumn("PublishingHouse");
        model.addColumn("Language");
        model.addColumn("Argument");
        model.addColumn("Title");
        model.addColumn("AccessModete");
        model.addColumn("Reprint");
        model.addColumn("ReleaseDate");
        model.addColumn("Release Location");
        model.addColumn("Presentation Name");
        model.addColumn("Series");
        model.addColumn("Author");
        table1.setModel(model);

        TableColumnModel cols = table1.getColumnModel();
        cols.getColumn(0).setPreferredWidth(250);
        cols.getColumn(1).setPreferredWidth(250);
        cols.getColumn(2).setPreferredWidth(200);
        cols.getColumn(3).setPreferredWidth(200);
        cols.getColumn(4).setPreferredWidth(150);
        cols.getColumn(5).setPreferredWidth(450);
        cols.getColumn(6).setPreferredWidth(150);
        cols.getColumn(7).setPreferredWidth(250);
        cols.getColumn(8).setPreferredWidth(450);
        cols.getColumn(9).setPreferredWidth(450);
        cols.getColumn(10).setPreferredWidth(200);
        cols.getColumn(11).setPreferredWidth(200);
    }

    private void createReprintBox() {
        reprintBox.addItem("true");
        reprintBox.addItem("false");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
